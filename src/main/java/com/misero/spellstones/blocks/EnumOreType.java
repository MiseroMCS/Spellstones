package com.misero.spellstones.blocks;

import com.misero.spellstones.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.ArrayList;


import com.misero.spellstones.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.ArrayList;


import com.misero.spellstones.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

public enum EnumOreType implements IStringSerializable {
    CORRUNDUM(0,"corrundum", 2, new ItemStack(ModItems.CORRUNDUM, 4)),
    MUNDANE_QUARTZ(1,"mundane_quartz", 2, new ItemStack(Items.QUARTZ, 2)),
    ENDER_CORRUNDUM(2, "ender_corrundum", 2, new ItemStack(ModItems.CORRUNDUM, 8));

    private String name;
    public int meta;
    public int harvestLevel;
    public List<ItemStack> drops;

    EnumOreType(int meta, String name, int harvestLevel, ItemStack... drops){
        this.name = name;
        this.meta = meta;
        this.harvestLevel = harvestLevel;
        this.drops = new ArrayList<ItemStack>();
        for(ItemStack stack : drops){
            this.drops.add(stack);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
