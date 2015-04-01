package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.blocks.BlockLoader;
import com.mcgoodtime.gti.common.recipes.CraftingLoader;
import com.mcgoodtime.gti.common.items.ItemLoader;
import com.mcgoodtime.gti.common.blocks.machines.MachineLoader;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod (
	modid = Gti.MODID ,
	version = Gti.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:IC2@[2.2.660,);"
	)

public final class Gti {
    public static final String MODID = "gti";
    public static final String VERSION = "Dev 0.0.4";

    public static CreativeTabGti creativeTabGTI = new CreativeTabGti();

    @Instance(Gti.MODID)
    public static Gti instance;

    @SidedProxy(serverSide = "com.mcgoodtime.gti.common.core.CommonProxy", clientSide = "com.mcgoodtime.gti.client.ClientProxy")
    public static CommonProxy commonProxy;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	BlockLoader.preInit();
    	ItemLoader.preInit();
    	MachineLoader.preInit();
    	CraftingLoader.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
