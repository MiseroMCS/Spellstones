package com.misero.spellstones.world;

import com.misero.spellstones.blocks.BlockOre;
import com.misero.spellstones.blocks.EnumOreType;
import com.misero.spellstones.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;
import com.google.common.base.Predicate;

public class WorldGenOre implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){

        switch(world.provider.getDimension()){
            case 0: genSurface(world, random, chunkX, chunkZ); break;
            case 1: genSurface(world, random, chunkX, chunkZ); break;
        }
    }

    private void genSurface(World world, Random random, int chunkX, int chunkZ){
        addOreSpawn(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.CORRUNDUM), world, random, chunkX, chunkZ, 16, 16, 5, 20, 1, 100, Blocks.STONE.getDefaultState());
        addOreSpawn(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.MUNDANE_QUARTZ), world, random, chunkX, chunkZ, 16, 16, 5, 20, 10, 100, Blocks.STONE.getDefaultState());
        addOreSpawn(ModBlocks.ORE.getDefaultState().withProperty(BlockOre.oreType, EnumOreType.ENDER_CORRUNDUM), world, random, chunkX, chunkZ, 16, 16, 5, 20, 10, 100, Blocks.END_STONE.getDefaultState());
    }

    private void addOreSpawn(IBlockState block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chance, int minY, int maxY, IBlockState spawnIn){
        int diffMinMaxY = maxY - minY;
        for(int x = 0; x < chance; x++){
            int posX = blockXPos * maxX + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffMinMaxY);
            int posZ = blockZPos * maxZ + random.nextInt(maxZ);
            Predicate<IBlockState> isBlock = i -> (i == spawnIn);
            new WorldGenMinable(block, maxVeinSize, isBlock).generate(world, random, new BlockPos(posX, posY, posZ));
        }
    }
}
