package com.misero.spellstones.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class PlayerMessages {
    public static void sendStatus(EntityPlayer player, String message){
        player.sendStatusMessage(new TextComponentString(message), true);
    }
}
