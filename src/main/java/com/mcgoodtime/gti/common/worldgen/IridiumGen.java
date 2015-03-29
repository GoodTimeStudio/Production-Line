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
