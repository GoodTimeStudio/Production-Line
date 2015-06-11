package com.mcgoodtime.gti.common.worldgen;


import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import ic2.api.item.IC2Items;

import java.util.Random;

/**
 * Another world generator?
 *
 * @author JAVA10
 */

public class BsaltGen implements IWorldGenerator {
    private static final int TICKET = 5;
    private static final int MAX_HEIGHT = 27;
    private static final int GEN_SIZE = 10;
	
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (!world.provider.isHellWorld && !world.provider.hasNoSky)
           generateOre(world, random, chunkX, chunkZ);
    }

    private void generateOre(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < this.TICKET; k++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = rand.nextInt(this.MAX_HEIGHT);
            int z = chunkZ * 16 + rand.nextInt(16);

            new WorldGenMinable(IC2Items.getItem("Basalt"), this.GEN_SIZE).generate(world, rand, x, y, z);
        }
    }
}
