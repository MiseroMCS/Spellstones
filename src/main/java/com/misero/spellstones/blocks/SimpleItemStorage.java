package com.misero.spellstones.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class SimpleItemStorage extends ItemStackHandler {
    public SimpleItemStorage(int size){
        super(size);
    }

    public ItemStack addStack(ItemStack stack, boolean simulate){
        if(stack == null){ return null; }
        for(int i=0; i < this.getSlots(); i++) {
            stack = insertItem(i, stack, simulate);
            if(stack == null){
                return stack;
            }
        }
        return stack;
    }

    public ItemStack getFirstStack(int amount, boolean simulate){
        ItemStack stack = null;
        for(int i=0; i < this.getSlots(); i++) {
            stack = extractItem(i, amount, simulate);
            if(stack != null){
                return stack;
            }
        }
        return stack;
    }

    public NonNullList<ItemStack> getItems(){
        NonNullList<ItemStack> stacks = NonNullList.create();
        for(int i=0; i < this.getSlots(); i++){
            ItemStack stack = this.extractItem(i, 64, true);
            if(stack == null){
                stack = new ItemStack(Blocks.AIR, 1);
            }
            stacks.add(stack);
        }
        return stacks;
    }
}
