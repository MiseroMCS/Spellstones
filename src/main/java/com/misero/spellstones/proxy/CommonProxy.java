package com.misero.spellstones.proxy;

import com.misero.spellstones.world.WorldGenOre;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id){}
    public void init(FMLInitializationEvent event){
        GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
    }
}
