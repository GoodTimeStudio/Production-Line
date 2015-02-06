package goodtime.mod.Industrial.common.core;

import java.util.Random;

import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import goodtime.mod.Industrial.common.Items.Food;
import goodtime.mod.Industrial.common.Items.OreItem;
import goodtime.mod.Industrial.common.Machine.ic2.MachineRecipes;
import goodtime.mod.Industrial.common.WorldGenerator.WorldGenerator;
import goodtime.mod.Industrial.common.block.Ore;


@Mod (
	modid = Industrial.MODID ,
	version = Industrial.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:Buildcraft|Core@[6.3.0,);"
					+ "after:IC2@[2.2.660,);"
	)


public final class Industrial {
	public static final String MODID = "GTI";
    public static final String VERSION = "Dev 0.0.1";
    
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) { 
    	
    	CreativeTabGTI.tab = new CreativeTabGTI(0, "GoodTime Industrial");	
    	Ore.preInit(event);
    	OreItem.preInit(event);
    	Food.preInit(event);
    	MachineRecipes.initRecipes();
    }
    

}
