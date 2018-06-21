package com.misero.spellstones.blocks.lenschamber;

import com.misero.spellstones.Spellstones;
import com.misero.spellstones.blocks.BlockTileEntity;
import com.misero.spellstones.util.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLensChamber extends BlockTileEntity<TileEntityLensChamber> implements IHasModel {

    public BlockLensChamber() {
        super("lens_chamber", Material.ROCK);
        setHardness(0.6f);
        setHarvestLevel("pickaxe", 2);
        setResistance(1.0f);
    }

    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }

    @Override
    public TileEntityLensChamber createTileEntity(World world, IBlockState state){
        return new TileEntityLensChamber();
    }

    @Override
    public Class<TileEntityLensChamber> getTileEntityClass() {
        return TileEntityLensChamber.class;
    }
}
