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

import cpw.mods.fml.common.IWorldGenerator;
import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Another world generator?
 *
 * @author JAVA10
 */

public class BasaltGen implements IWorldGenerator {
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
        for (int k = 0; k < TICKET; k++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = rand.nextInt(MAX_HEIGHT);
            int z = chunkZ * 16 + rand.nextInt(16);

            new WorldGenMinable(Block.getBlockFromItem(Ic2Items.basaltBlock.getItem()), GEN_SIZE).generate(world, rand, x, y, z);
        }
    }
}