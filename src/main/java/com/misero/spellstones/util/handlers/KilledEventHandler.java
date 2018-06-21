package com.misero.spellstones.util.handlers;

import com.misero.spellstones.items.ModItems;
import com.misero.spellstones.items.configurator.ItemConfigurator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class KilledEventHandler {
    @SubscribeEvent
    public void onKilledMob(LivingDeathEvent event){
        if (event.getEntity().getEntityWorld().isRemote) {
            return;
        }
        if(event.getEntity() instanceof EntityDragon){
            Entity killerEntity = event.getSource().getTrueSource();
            if(killerEntity instanceof EntityPlayer){
                EntityPlayer killer = (EntityPlayer)killerEntity;
                if(killer.inventory.hasItemStack(new ItemStack(ModItems.CONFIGURATOR))){
                    ItemConfigurator.sendLine(killer, "dragon_killed", false);
                }
            }
        }

    }
}
