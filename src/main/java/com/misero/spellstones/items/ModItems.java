package com.misero.spellstones.items;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.items.configurator.ItemConfigurator;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //MATERIALS
    public static final Item SPELLSTONE_DUST = new ItemBase("spellstone_dust");
    public static final Item SHATTERED_QUARTZ = new ItemBase("shattered_quartz");
    public static final Item CORRUNDUM = new ItemBase("corrundum");
    public static final Item TEMPERED_LENS = new ItemBase("tempered_lens");
    public static final Item CONFIGURATOR = new ItemConfigurator();

    public static final Item SPELLSTONE = new ItemSpellstone();
    public static void init(){
        SPELLSTONE_DUST.setCreativeTab(Spellstones.CREATIVE_TAB);
        SPELLSTONE.setCreativeTab(Spellstones.CREATIVE_TAB);
        SHATTERED_QUARTZ.setCreativeTab(Spellstones.CREATIVE_TAB);
        CORRUNDUM.setCreativeTab(Spellstones.CREATIVE_TAB);
        TEMPERED_LENS.setCreativeTab(Spellstones.CREATIVE_TAB);
        CONFIGURATOR.setCreativeTab(Spellstones.CREATIVE_TAB);
        OreDictionary.registerOre("gemSapphire", CORRUNDUM);
        OreDictionary.registerOre("dustQuartz", SHATTERED_QUARTZ);
    }
}