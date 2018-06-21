package com.misero.spellstones.blocks.lenschamber;

import com.misero.spellstones.blocks.SimpleItemStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityLensChamber extends TileEntity {
    SimpleItemStorage itemStorage;

    public TileEntityLensChamber(){
        itemStorage = new SimpleItemStorage(1);
    }

    public ItemStack getHeldItem(boolean remove){
        return itemStorage.getFirstStack(64, !remove);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing){
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return (T) itemStorage;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = itemStorage.serializeNBT();
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        itemStorage.deserializeNBT(compound);
        super.readFromNBT(compound);
    }

}
