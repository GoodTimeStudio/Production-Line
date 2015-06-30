package com.mcgoodtime.gti.common.worldgen;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.Random;

/**
 * A dummy world generator.
 */
public class DummyWorldGenerator extends WorldGenerator {
    /** The size of each pile of ores. */
    protected int generationSize;
    /** How many piles ores are estimated to exist in the chunk. */
    protected int generationNumber;
    protected int maxHeight;

    protected DummyWorldGenerator(int size, int number, int maxHeight) {
        super(false);
        this.generationSize = size;
        this.generationNumber = number;
        this.maxHeight = maxHeight;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        return generate(world, random, x << 2, z << 2);
    }

    public boolean generate(OreGenEvent event) {
        return generate(event.world, event.rand, event.worldX << 2, event.worldZ << 2);
    }

    public boolean generate(World world, Random random, int chunkX, int chunkZ) {
        return true;
    }
}
