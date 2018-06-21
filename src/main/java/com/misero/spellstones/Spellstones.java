package com.misero.spellstones;

import com.misero.spellstones.blocks.ModBlocks;
import com.misero.spellstones.items.ModItems;
import com.misero.spellstones.items.configurator.ItemConfigurator;
import com.misero.spellstones.misc.VillagerTrades;
import com.misero.spellstones.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Spellstones.MODID, name = Spellstones.NAME, version = Spellstones.VERSION)
public class Spellstones {
    //Basic mod info. Change these!
    public static final String MODID = "spellstones";
    public static final String NAME = "Spellstones";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static Spellstones instance;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("spellstones") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.CORRUNDUM);
        }
    };

    //Sets up the proxy - a way of running different code on a server or client instance of the game.
    @SidedProxy(clientSide="com.misero.spellstones.proxy.ClientProxy", serverSide="com.misero.spellstones.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    // Initializes log helper.
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    //Initialization - currently just for debug.
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ModItems.init();
        ModBlocks.init();
        VillagerRegistry.VillagerProfession librarian = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new ResourceLocation("minecraft:librarian"));
        librarian.getCareer(0).addTrade(1, new VillagerTrades());
        GameRegistry.addSmelting(ModItems.SPELLSTONE_DUST, new ItemStack(ModItems.SPELLSTONE,1), 100);
        GameRegistry.addSmelting(ModItems.SPELLSTONE, new ItemStack(ModItems.SPELLSTONE, 1), 0);
        ItemConfigurator.addLines();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
