package com.misero.spellstones.items;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.util.IHasModel;
import net.minecraft.item.Item;

import java.util.List;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String id){
        setUnlocalizedName(id);
        setRegistryName(Spellstones.MODID, id);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Spellstones.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
