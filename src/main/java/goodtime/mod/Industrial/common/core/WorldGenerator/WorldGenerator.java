package goodtime.mod.Industrial.common.core.WorldGenerator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import goodtime.mod.Industrial.common.block.Ore;


public class WorldGenerator  implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	}
	
	public static void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{  

		for(int i = 0; i < 50; i++) {
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(128);
			int randPosZ = chunkZ + random.nextInt(16);
			new WorldGenMinable(Ore.IR , 50).generate(world, random, randPosX, randPosY, randPosZ);
		}

	}

	public static void preInit(FMLPreInitializationEvent event) { 
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
	}
}
