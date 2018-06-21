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

import java.util.ArrayList;
import java.util.List;

public class BrightFocusProperties extends FocusPropertyHolder {
    public LinkedFocusPosition linked1 = new LinkedFocusPosition(0,0,0,0);
    public LinkedFocusPosition linked2 = new LinkedFocusPosition(0,0,0,0);
    public BrightFocusProperties() {
        super();
    }

    @Override
    public EnumSpellstoneColor getColor() {
        return EnumSpellstoneColor.RED;
    }

    @Override
    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state, LinkedFocusPosition selected) {
        if(selected == null){
            PlayerMessages.sendStatus(player, "No focus selected.");
        }else{
            if(selected.dim == world.provider.getDimension()){
                PlayerMessages.sendStatus(player, "Focuses are in different dimensions!");
            }else{
                if(linked1 == new LinkedFocusPosition(0,0,0,0)) {
                    linked1 = selected;
                }else{
                    linked2 = selected;
                }
            }
        }
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("linked1", linked1.serializeNBT());
        nbt.setTag("linked2", linked2.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        linked1 = new LinkedFocusPosition(nbt.getCompoundTag("linked1"));
        linked2 = new LinkedFocusPosition(nbt.getCompoundTag("linked2"));
    }

    @Override
    public FocusPropertyHolder create(){
        return new BrightFocusProperties();
    }
}
