package com.misero.spellstones.items.configurator;

import com.misero.spellstones.blocks.BlockOre;
import com.misero.spellstones.blocks.EnumOreType;
import com.misero.spellstones.blocks.ModBlocks;
import com.misero.spellstones.items.ItemBase;
import com.misero.spellstones.util.IHasModel;
import com.misero.spellstones.util.NBTHelper;
import com.misero.spellstones.util.UtilMisc;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ItemConfigurator extends ItemBase implements IHasModel {
    public static final Map<IBlockState, String> lines = new HashMap<>();
    public ItemConfigurator() {
        super("configurator");
        setMaxStackSize(1);
    }

    public static void sendLine(EntityPlayer player, String line, boolean haveName){
        String prefix = ""; if(haveName) {prefix = "<Configurator> ";}
        player.sendStatusMessage(new TextComponentString(prefix + I18n.format("item.configurator.lines." + line)), false);
    }

    public LinkedFocusPosition getLinked(ItemStack stack){
        NBTTagCompound nbt = NBTHelper.getTagCompoundSafe(stack);
        if(nbt.hasKey("linkedX") && nbt.hasKey("linkedY") && nbt.hasKey("linkedZ") && nbt.hasKey("linkedDim")) {
            return new LinkedFocusPosition(nbt);
        }else{
            return null;
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(!worldIn.isRemote) {
            IBlockState blockState = worldIn.getBlockState(pos);
            if (blockState.getBlock() instanceof IConfigurable) { //actually configure the block
                LinkedFocusPosition linked = getLinked(player.getHeldItem(hand));
                ((IConfigurable) blockState.getBlock()).configure(worldIn, player, hand, pos, blockState, linked);
            } else { //slightly pointless flavor text
                addLines();
                String line = lines.getOrDefault(blockState, "default");
                sendLine(player, line, true);
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, java.util.List<String> tooltip, ITooltipFlag flagIn) {
        int dim;
        if(worldIn == null){ dim = 0;} else{ dim = worldIn.provider.getDimension(); }
        if(dim == 0) {
            tooltip.add(I18n.format("item.configurator.lore.overworld"));
        }else if(dim == 1){
            tooltip.add(I18n.format("item.configurator.lore.end"));
        }else if(dim == -1){
            tooltip.add(I18n.format("item.configurator.lore.nether"));
        }else{
            tooltip.add(I18n.format("item.configurator.lore.confused"));
        }
    }

    public static void addLines(){
        lines.put(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.CORRUNDUM), "corrundum");
        lines.put(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.MUNDANE_QUARTZ), "mundane_quartz");
        lines.put(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.ENDER_CORRUNDUM), "ender_corrundum");
        lines.put(Blocks.END_STONE.getDefaultState(), "end_stone");
        lines.put(Blocks.END_BRICKS.getDefaultState(), "end_bricks");
        lines.put(Blocks.BEDROCK.getDefaultState(), "bedrock");
        lines.put(Blocks.REDSTONE_WIRE.getDefaultState(), "redstone");
        lines.put(Blocks.REDSTONE_ORE.getDefaultState(), "redstone");
        lines.put(Blocks.LIT_REDSTONE_ORE.getDefaultState(), "redstone");
        lines.put(Blocks.REDSTONE_BLOCK.getDefaultState(), "redstone_block");
        lines.put(Blocks.NETHERRACK.getDefaultState(), "netherrack");
        lines.put(ModBlocks.LENS_CHAMBER.getDefaultState(), "lens_chamber");
        lines.put(Blocks.COAL_ORE.getDefaultState(), "coal");
        lines.put(Blocks.IRON_ORE.getDefaultState(), "iron");
        lines.put(Blocks.DIAMOND_ORE.getDefaultState(), "diamond");
    }
}
