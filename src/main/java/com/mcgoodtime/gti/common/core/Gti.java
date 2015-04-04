/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.blocks.OreIridium;
import com.mcgoodtime.gti.common.blocks.machines.GenGasKU;
import com.mcgoodtime.gti.common.recipes.CraftingLoader;
import com.mcgoodtime.gti.common.items.ItemLoader;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod (
	modid = this.MODID ,
    name = this.MODNAME,
	version = this.VERSION ,
	dependencies = "required-after:Forge@[10.13.0.1230,);"
					+ "after:IC2@[2.2.660,);"
	)

public final class Gti {
    public static final String MODID = "gti";
    public static final String MODNAME = "GoodTime Industrial";
    public static final String VERSION = "Dev 0.0.4";
    public static final String RESOURCE_DOMAIN = "gti";
    public static final String GUI_PREFIX = "gui.gti.";

    @Instance(Gti.MODID)
    public static Gti instance;

    @SidedProxy(serverSide = "com.mcgoodtime.gti.common.core.CommonProxy", clientSide = "com.mcgoodtime.gti.client.ClientProxy")
    public static CommonProxy commonProxy;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OreIridium.preInit();
    	ItemLoader.preInit();
        GameRegistry.registerBlock(GenGasKU.GenGasKU, "GenGasKU");
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
