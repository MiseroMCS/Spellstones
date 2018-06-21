package com.misero.spellstones.blocks.focus.properties;

import com.misero.spellstones.blocks.focus.EnumSpellstoneColor;
import com.misero.spellstones.items.configurator.LinkedFocusPosition;
import com.misero.spellstones.util.PlayerMessages;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlueFocusProperties extends FocusPropertyHolder {
    public boolean drop;
    public BlueFocusProperties() {
        super();
    }

    @Override
    public EnumSpellstoneColor getColor() {
        return EnumSpellstoneColor.BLUE;
    }

    @Override
    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state, LinkedFocusPosition selected) {
        drop = !drop;
        if(drop) {
            PlayerMessages.sendStatus(player, "Drop Unusable Items: enabled.");
        }else{
            PlayerMessages.sendStatus(player, "Drop Unusable Items: disabled.");
        }
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("dropItem", drop);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        drop = nbt.getBoolean("dropItem");
    }

    @Override
    public FocusPropertyHolder create(){
        return new BlueFocusProperties();
    }
}
