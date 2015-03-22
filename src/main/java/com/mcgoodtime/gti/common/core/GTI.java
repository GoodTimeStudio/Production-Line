package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.block.BlockLoader;
import com.mcgoodtime.gti.common.core.Crafting.CraftingLoader;
import com.mcgoodtime.gti.common.Items.ItemLoader;
import com.mcgoodtime.gti.common.block.Machine.MachineLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod (
	modid = GTI.MODID ,
	version = GTI.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:Buildcraft|Core@[6.3.0,);"
					+ "after:IC2@[2.2.660,);"
					+ "after:Forestry@[3.4.0.7,);"
	)

public final class GTI {
	//@SidedProxy (serverSide = "com.mcgoodtime.gti.common.core.CommonProxy", clientSide = "com.mcgoodtime.gti.client.ClientProxy")

    public static final String MODID = "gti";
    public static final String VERSION = "Dev 0.0.4";
    
    @Instance(GTI.MODID)
    public static GTI modInstance;
   
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
