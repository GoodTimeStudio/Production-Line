package goodtime.mod.Industrial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goodtime.mod.Industrial.CreativeTabGTI;
import goodtime.mod.Industrial.core.Ore;

@Mod (modid = Industrial.MODID , version = Industrial.VERSION)
public class Industrial {
	public static final String MODID = "GTI";
    public static final String VERSION = "Dev 0203";
    
    
    @EventHandler 
    public void preInit(FMLPreInitializationEvent event) { 
    	CreativeTabGTI.tab = new CreativeTabGTI(0, "GoodTime Industrial");
    	
    	Ore.preInit(event);
    }

}
