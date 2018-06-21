package com.misero.spellstones.blocks.focus.properties;

import com.misero.spellstones.blocks.focus.EnumSpellstoneColor;
import com.misero.spellstones.items.configurator.LinkedFocusPosition;
import com.misero.spellstones.util.PlayerMessages;
import com.sun.org.apache.xml.internal.security.utils.I18n;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class YellowFocusProperties extends FocusPropertyHolder {
    public int delay;
    public YellowFocusProperties() {
        super();
        delay = 20;
    }

    @Override
    public EnumSpellstoneColor getColor() {
        return EnumSpellstoneColor.RED;
    }

    @Override
    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state, LinkedFocusPosition selected) {
        if(player.isSneaking()){
            if(delay != 1) {
                delay--;
                PlayerMessages.sendStatus(player, I18n.translate("tile.focus_yellow.change", new Object[]{delay}));
            }else{
                PlayerMessages.sendStatus(player, I18n.translate("tile.focus_yellow.fail"));
            }
        }else{
            delay++;
            PlayerMessages.sendStatus(player, I18n.translate("tile.focus_yellow.change", new Object[]{delay}));
        }
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("timerDelay", delay);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        delay = nbt.getInteger("timerDelay");
    }

    @Override
    public FocusPropertyHolder create(){
        return new YellowFocusProperties();
    }
}
