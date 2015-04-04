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

import com.mcgoodtime.gti.common.blocks.OreIridium;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class IridiumGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateSurface(world, random, chunkX, chunkZ);
	}
	
	public static void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		for (int i = 0; i < 5; i++) {
			int randPosX = chunkX * 16 + random.nextInt(16);
			int randPosY = random.nextInt(16);
			int randPosZ = chunkZ * 16 + random.nextInt(16);
			new WorldGenMinable(OreIridium.IR , 50).generate(world, random, randPosX, randPosY, randPosZ);
		}

	}

	public static void preInit(FMLPreInitializationEvent event) {
		//MinecraftForge.ORE_GEN_BUS.register();
	}
}
