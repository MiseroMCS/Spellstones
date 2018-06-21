package com.misero.spellstones.blocks.focus;

import cofh.core.util.helpers.ItemHelper;
import com.misero.spellstones.blocks.ModBlocks;
import com.misero.spellstones.blocks.SimpleItemStorage;
import com.misero.spellstones.blocks.focus.properties.FocusPropertyHolder;
import com.misero.spellstones.blocks.focus.properties.RedFocusProperties;
import com.misero.spellstones.util.UtilMisc;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.List;

public class TileEntityFocus extends TileEntity {
    public FocusPropertyHolder properties;

    public EnergyStorage energyStorage;
    public SimpleItemStorage itemStorage;

    public int i = 0;

    public TileEntityFocus(){
        energyStorage = new EnergyStorage(100000, 100, 100);
        itemStorage = new SimpleItemStorage(6);
        this.properties = new RedFocusProperties();
    }

    public TileEntityFocus(EnumSpellstoneColor color){
        energyStorage = new EnergyStorage(100000, 100, 100);
        itemStorage = new SimpleItemStorage(6);
        this.properties = FocusPropertyHolder.fromColor(color);
    }

    public void act(EntityPlayer player){
        if(properties.getColor() == null){
            this.properties = FocusPropertyHolder.fromColor(EnumSpellstoneColor.RED);
        }
        World world = this.getWorld();
        BlockPos pos = this.getPos();
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = state.getValue(BlockFocus.FACING);
        BlockPos action = UtilMisc.inFrontOf(pos, facing);
        EnumSpellstoneColor held = this.properties.getColor();
        switch(held){
            case RED: red(world, pos, state, facing, action); break;
            case GREEN: green(world, pos, state, facing, action); break;
            case BLUE: blue(world, pos, state, facing, action); break;
        }
    }
    public void dim(World world, BlockPos pos, IBlockState state, EnumFacing facing, BlockPos action){

    }

    public void red(World world, BlockPos pos, IBlockState state, EnumFacing facing, BlockPos action){
        RedFocusProperties redProperties = (RedFocusProperties)properties;
        if(energyStorage.extractEnergy(100, false) != 0) {
            IBlockState destroyedState = world.getBlockState(action);
            List<ItemStack> stacks = UtilMisc.simulateBreaking(world, action, destroyedState, 0);
            for(ItemStack stack : stacks) {
                if (redProperties.drop) {
                    Block.spawnAsEntity(world, action, stack);
                } else {
                    itemStorage.addStack(stack, false);
                }
            }
            world.destroyBlock(action, false);
        }
    }

    public void green(World world, BlockPos pos, IBlockState state, EnumFacing facing, BlockPos action){
        IBlockState toRefine = world.getBlockState(action);
        GreenAugmentRecipe recipe = GreenAugmentManager.getRecipe(world, action, toRefine);
        if(recipe == null){
            return;
        }
        int energy = recipe.energy;
        ItemStack result = recipe.result;
        if(energyStorage.extractEnergy(energy, false) != 0) {
            itemStorage.addStack(result, false);
            if(toRefine.getBlock() != ModBlocks.LENS_CHAMBER) {
                world.destroyBlock(action, false);
            }
        }
    }

    public void blue(World world, BlockPos pos, IBlockState state, EnumFacing facing, BlockPos action){
        ItemStack stack = itemStorage.getFirstStack(1, false);
        if (!(stack.getItem() instanceof ItemAir)){
            if(stack.getItem() instanceof ItemBlock){
                ItemBlock itemBlock = ((ItemBlock)stack.getItem());
                IBlockState placedState = itemBlock.getBlock().getStateFromMeta(itemBlock.getMetadata(stack));
                IBlockState actionState = world.getBlockState(action);
                if(actionState.getBlock().isAir(actionState, world, pos)){
                    if(energyStorage.extractEnergy(10, false) != 0) {
                        world.setBlockState(action, placedState);
                    }
                }
            }else{
                Block.spawnAsEntity(world, action, stack);
            }
        }
    }

    public void bright(World world, BlockPos pos, IBlockState state, EnumFacing facing, BlockPos action){

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing){
        if(capability == CapabilityEnergy.ENERGY){
            return true;
        }else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return (T) energyStorage;
        }else if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return (T) itemStorage;
        }
        return super.getCapability(capability, facing);
    }

    public void configure(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state){

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = itemStorage.serializeNBT();
        compound = properties.serializeNBT();
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        itemStorage.deserializeNBT(compound);
        properties.deserializeNBT(compound);
        super.readFromNBT(compound);
    }
}
