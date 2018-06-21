package com.misero.spellstones.blocks.focus.properties;

import com.misero.spellstones.blocks.focus.EnumSpellstoneColor;
import com.misero.spellstones.items.configurator.IConfigurable;
import com.misero.spellstones.items.configurator.LinkedFocusPosition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

public class FocusPropertyHolder implements INBTSerializable<NBTTagCompound>, IConfigurable {

    public EnumSpellstoneColor getColor(){
        return null;
    }

    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state, LinkedFocusPosition selected){

    }

    public NBTTagCompound serializeNBT(){
        return null;
    }

    public void deserializeNBT(NBTTagCompound nbt){

    }

    public static FocusPropertyHolder fromColor(EnumSpellstoneColor color){
        try {
            return color.propertyClass.newInstance();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public FocusPropertyHolder create(){ return null; }
}
