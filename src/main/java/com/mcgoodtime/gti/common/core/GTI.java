package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.client.gui.GUIGenGasKU;
import com.mcgoodtime.gti.common.TileEntity.TileEntityGenGasKU;
import com.mcgoodtime.gti.common.block.BlockLoader;
import com.mcgoodtime.gti.common.container.ContainerGenGasKU;
import com.mcgoodtime.gti.common.core.Crafting.CraftingLoader;
import com.mcgoodtime.gti.common.Items.ItemLoader;
import com.mcgoodtime.gti.common.block.Machine.MachineLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@Mod (
	modid = GTI.MODID ,
	version = GTI.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:Buildcraft|Core@[6.3.0,);"
					+ "after:IC2@[2.2.660,);"
					+ "after:Forestry@[3.4.0.7,);"
	)

public final class GTI implements IGuiHandler {
	//@SidedProxy (serverSide = "com.mcgoodtime.gti.common.core.CommonProxy", clientSide = "com.mcgoodtime.gti.client.ClientProxy")

    public static final String MODID = "gti";
    public static final String VERSION = "Dev 0.0.4";
    
    @Instance(GTI.MODID)
    public static GTI modInstance;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) { 
    	
    	CreativeTabGTI.tab = new CreativeTabGTI("GoodTime Industrial");
    	
    	BlockLoader.preInit();
    	ItemLoader.preInit();
    	MachineLoader.preInit();
    	CraftingLoader.preInit();
    	
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, this);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //switch (ID) {
        //    case 10:
        //        return new GUIGenGasKU((ContainerGenGasKU)player.inventoryContainer, (TileEntityGenGasKU)player.getEntityWorld().getTileEntity(x, y, z));
        //}
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
