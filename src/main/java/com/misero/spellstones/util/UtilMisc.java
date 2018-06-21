package com.misero.spellstones.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class UtilMisc {
    public static BlockPos inFrontOf(BlockPos pos, EnumFacing facing){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        switch(facing){
            case UP: y++; break;
            case DOWN: y--; break;
            case NORTH: z--; break;
            case SOUTH: z++; break;
            case EAST: x--; break;
            case WEST: x++; break;
        }
        return new BlockPos(x,y,z);
    }

    public static List<ItemStack> simulateBreaking(World worldIn, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> selected = new ArrayList<>();
        if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) { // do not drop items while restoring blockstates, prevents item dupe
            List<ItemStack> drops = state.getBlock().getDrops(worldIn, pos, state, fortune); // use the old method until it gets removed, for backward compatibility
            float chance = 100.0f; //not sure what else to do
            for (ItemStack drop : drops) {
                if (worldIn.rand.nextFloat() <= chance) {
                    selected.add(drop);
                }
            }
        }
        return selected;
    }
}
