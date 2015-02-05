package goodtime.mod.Industrial.common.core;

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

	private WorldGenMinable IRGenerator;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
	}
	
	public void generateSurface(World world, java.util.Random rand, int chunkX, int chunkZ){
		for(int i = 0; i < 2; i++){
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(40)+7;
			int randPosZ = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(Ore.IR, 32)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}
}
