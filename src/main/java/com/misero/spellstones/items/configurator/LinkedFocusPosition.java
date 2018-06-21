package com.misero.spellstones.items.configurator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

public class LinkedFocusPosition implements INBTSerializable<NBTTagCompound> {
    public int x;
    public int y;
    public int z;
    public int dim;
    public LinkedFocusPosition(int x, int y, int z, int dim){
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
    }

    public LinkedFocusPosition(NBTTagCompound nbt){
        deserializeNBT(nbt);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("linkedX", x);
        nbt.setInteger("linkedY", y);
        nbt.setInteger("linkedZ", z);
        nbt.setInteger("linkedDim", dim);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        x = nbt.getInteger("linkedX");
        y = nbt.getInteger("linkedY");
        z = nbt.getInteger("linkedZ");
        dim = nbt.getInteger("linkedDim");
    }
}
