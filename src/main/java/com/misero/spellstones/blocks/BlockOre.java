package com.misero.spellstones.blocks;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.items.ModItems;
import com.misero.spellstones.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.FakePlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlockOre extends Block implements IHasModel {
    public static final PropertyEnum<EnumOreType> oreType = PropertyEnum.create("oretype", EnumOreType.class);
    public static List<String> names = new ArrayList<>();
    static {
        names.add("tile.corrundum_ore");
        names.add("tile.mundane_quartz_ore");
        names.add("tile.ender_corrundum");
    }
    public BlockOre(){
        super(Material.ROCK);
        setUnlocalizedName("ore");
        setRegistryName(Spellstones.MODID, "ore");
        setCreativeTab(Spellstones.CREATIVE_TAB);
        setHardness(0.6f);
        setResistance(1.0f);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlockNamed(this, names));
    }

    @Override
    public int getHarvestLevel(IBlockState state){
        int harvestLevel = state.getValue(oreType).harvestLevel;
        return harvestLevel;
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, oreType);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        EnumOreType blockOreType = state.getValue(oreType);
        for(ItemStack stack : blockOreType.drops){
            drops.add(stack);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state){
        EnumOreType blockOreType = state.getValue(oreType);
        return blockOreType.meta;
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(oreType, EnumOreType.values()[meta]);
    }

    public void registerModels() {
        Spellstones.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "oretype=corrundum");
        Spellstones.proxy.registerItemRenderer(Item.getItemFromBlock(this), 1, "oretype=mundane_quartz");
        Spellstones.proxy.registerItemRenderer(Item.getItemFromBlock(this), 1, "oretype=ender_corrundum");
    }
}
