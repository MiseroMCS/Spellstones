package com.misero.spellstones.blocks.focus;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.blocks.BlockTileEntity;
import com.misero.spellstones.items.configurator.IConfigurable;
import com.misero.spellstones.items.configurator.LinkedFocusPosition;
import com.misero.spellstones.util.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockFocus extends BlockTileEntity<TileEntityFocus> implements IHasModel,IConfigurable {
    public EnumSpellstoneColor held;
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockFocus(EnumSpellstoneColor color){
        super("focus_" + color.toString().toLowerCase(), Material.ROCK);
        setHardness(0.6f);
        setHarvestLevel("pickaxe", 2);
        setResistance(1.0f);
        this.held = color;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(world.isRemote){
            return true; //BAD CLIENT, BAD BOY. NO CRASHING SERVERS!
        }
        TileEntityFocus tileEntity = (TileEntityFocus) world.getTileEntity(pos);
        if(player.isSneaking()){
            tileEntity.act(player);
        }else {
            player.sendStatusMessage(new TextComponentString("Energy: " + tileEntity.energyStorage.getEnergyStored() + "/" + tileEntity.energyStorage.getMaxEnergyStored()), true);
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }

    @Override
    public Class<TileEntityFocus> getTileEntityClass() {
        return TileEntityFocus.class;
    }

    @Override
    public TileEntityFocus createTileEntity(World world, IBlockState state){
        return new TileEntityFocus(held);
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        int i = 0;
        EnumFacing enumFacing = state.getValue(FACING);

        switch(enumFacing){
            case NORTH: i=0; break;
            case EAST: i=1; break;
            case SOUTH: i=2; break;
            case WEST: i=3; break;
            case UP: i=4; break;
            case DOWN: i=5; break;
        }

        return i;
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        EnumFacing enumFacing = EnumFacing.UP;
        switch(meta){
            case 0: enumFacing = EnumFacing.NORTH; break;
            case 1: enumFacing = EnumFacing.EAST; break;
            case 2: enumFacing = EnumFacing.SOUTH; break;
            case 3: enumFacing = EnumFacing.WEST; break;
            case 4: enumFacing = EnumFacing.UP; break;
            case 5: enumFacing = EnumFacing.DOWN;
        }
        return this.getDefaultState().withProperty(FACING, enumFacing);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public void registerModels() {
        Spellstones.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "facing=north");
    }

    @Override
    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state, LinkedFocusPosition selected) {
        TileEntityFocus tileEntity = (TileEntityFocus) world.getTileEntity(pos);
        tileEntity.configure(world, player, hand, pos, state);
    }
}
