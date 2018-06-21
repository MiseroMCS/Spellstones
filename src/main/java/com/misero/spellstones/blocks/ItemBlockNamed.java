package com.misero.spellstones.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockNamed extends ItemColored {
    public List<String> names;
    public ItemBlockNamed(Block oreBlock, List<String> names) {
        super(oreBlock, true);
        this.names = names;
        setRegistryName(oreBlock.getRegistryName());
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        String unlocalizedName = names.get(stack.getMetadata());
        return unlocalizedName;
    }
}
