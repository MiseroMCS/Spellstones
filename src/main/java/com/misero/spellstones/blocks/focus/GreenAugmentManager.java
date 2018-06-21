package com.misero.spellstones.blocks.focus;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.blocks.ModBlocks;
import com.misero.spellstones.blocks.lenschamber.TileEntityLensChamber;
import com.misero.spellstones.items.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class GreenAugmentManager {
    private static final Map<Item, GreenAugmentRecipe> LENS_CHAMBER_RECIPES = new HashMap<>();
    public static final Map<IBlockState, GreenAugmentRecipe> IN_WORLD_RECIPES = new HashMap<>();

    public static GreenAugmentRecipe augmentItem(Item item) {
        GreenAugmentRecipe recipe = LENS_CHAMBER_RECIPES.get(item);
        return recipe;
    }

    public static GreenAugmentRecipe augmentBlock(IBlockState blockState){
        GreenAugmentRecipe recipe = IN_WORLD_RECIPES.get(blockState);
        return recipe;
    }

    public static GreenAugmentRecipe getRecipe(World world, BlockPos pos, IBlockState state){
        loadRecipes();
        if(state.getBlock() == ModBlocks.LENS_CHAMBER){
            TileEntityLensChamber tileEntity = (TileEntityLensChamber)world.getTileEntity(pos);
            ItemStack stack = tileEntity.getHeldItem(true);
            return augmentItem(stack.getItem());
        }else{
            return augmentBlock(state);
        }
    }

    public static void addLensChamberRecipe(Item input, ItemStack output, int energy){
        GreenAugmentRecipe recipe = new GreenAugmentRecipe(output, energy);
        LENS_CHAMBER_RECIPES.put(input, recipe);
    }

    public static void addInWorldRecipe(IBlockState input, ItemStack output, int energy){
        IN_WORLD_RECIPES.put(input, new GreenAugmentRecipe(output, energy));
    }

    public static void loadRecipes(){
        addInWorldRecipe(Blocks.GLASS_PANE.getDefaultState(), new ItemStack(ModItems.TEMPERED_LENS, 2), 100);
        addLensChamberRecipe(ModItems.CORRUNDUM, new ItemStack(ModItems.SPELLSTONE, 1, 4), 100);
    }
}
