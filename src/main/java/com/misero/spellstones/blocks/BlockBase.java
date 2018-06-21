package com.misero.spellstones.blocks;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.items.ModItems;
import com.misero.spellstones.util.IHasModel;
import com.pengu.hammercore.HammerCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemColored;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String id, Material blockMaterial) {
        super(blockMaterial);
        setUnlocalizedName(id);
        setRegistryName(Spellstones.MODID, id);
        setCreativeTab(Spellstones.CREATIVE_TAB);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemColored(this, true).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Spellstones.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
