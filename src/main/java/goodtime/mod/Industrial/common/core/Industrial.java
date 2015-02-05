package goodtime.mod.Industrial.common.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goodtime.mod.Industrial.common.block.CreativeTabGTI;
import goodtime.mod.Industrial.common.block.Ore;

@Mod (modid = Industrial.MODID , version = Industrial.VERSION)
public final class Industrial {
	public static final String MODID = "GTI";
    public static final String VERSION = "Dev 0203";
    
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) { 
    	CreativeTabGTI.tab = new CreativeTabGTI(0, "GoodTime Industrial");
    	
    	Ore.preInit(event);
    }

}
