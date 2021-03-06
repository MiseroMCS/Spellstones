package com.misero.spellstones.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {
    public BlockTileEntity(String name, Material material){
            super(name, material);
    }

    public abstract Class<TE> getTileEntityClass();

    public TE getTileEntity(IBlockAccess world, BlockPos pos){
        return (TE)world.getTileEntity(pos);
    }
}
