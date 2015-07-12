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

import com.mcgoodtime.gti.common.init.GtiBlocks;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * The class for Iridium WorldGen.
 *
 * @author Su Hao
 */
public class IridiumGen extends DummyWorldGenerator {

    protected IridiumGen() {
        super(3, 5, 16);
    }

    private static IridiumGen instance = new IridiumGen();

    public static IridiumGen getInstance() {
        return instance;
    }

    public static void setInstance(int size, int number, int maxHeight) {
        instance.generationSize = size;
        instance.generationNumber = number;
        instance.maxHeight = maxHeight;
    }

    @Override
    public boolean generate(World world, Random rand, int chunkX, int chunkZ) {
        if (world.provider.isHellWorld || world.provider.hasNoSky) {
            return true;
        }
        boolean ret = true;
        for (int k = 0; k < this.generationNumber; k++) {
            int x = chunkX << 4 + rand.nextInt(16);
            int y = rand.nextInt(this.maxHeight);
            int z = chunkZ << 4 + rand.nextInt(16);

            ret = ret && new WorldGenMinable(GtiBlocks.oreIridium, this.generationSize)
                    .generate(world, rand, x, y, z);
        }
        return ret;
    }
}
