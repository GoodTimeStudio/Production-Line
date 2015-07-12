/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
        return generate(world, random, x >> 2, z >> 2);
    }

    public boolean generate(OreGenEvent event) {
        return generate(event.world, event.rand, event.worldX >> 2, event.worldZ >> 2);
    }

    public boolean generate(World world, Random random, int chunkX, int chunkZ) {
        return true;
    }
}
