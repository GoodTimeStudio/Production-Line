package goodtime.mod.Industrial.common.core;

import goodtime.mod.Industrial.common.Items.ItemLoader;
import goodtime.mod.Industrial.common.Machine.MachineLoader;
import goodtime.mod.Industrial.common.block.BlockLoader;
import goodtime.mod.Industrial.common.core.Crafting.CraftingLoader;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod (
	modid = Industrial.MODID ,
	version = Industrial.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:Buildcraft|Core@[6.3.0,);"
					+ "after:IC2@[2.2.660,);"
					+ "after:Forestry@[3.4.0.7,);"
	)

public final class Industrial {
	@SidedProxy (serverSide = "goodtime.mod.Industrial.common.core.CommonProxy",clientSide = "goodtime.mod.Industrial.client.ClientProxy")
	public static CommonProxy proxy;
	public static final String MODID = "GTI";
    public static final String VERSION = "Dev 0.0.3"; 
    
    @Instance(Industrial.MODID)
    public static Industrial modInstance;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) { 
    	
    	CreativeTabGTI.tab = new CreativeTabGTI(0, "GoodTime Industrial");	
    	
    	BlockLoader.preInit();
    	ItemLoader.preInit();
    	MachineLoader.preInit();
    	CraftingLoader.preInit();
    	
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	MachineLoader.init();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
