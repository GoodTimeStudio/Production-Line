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

import com.mcgoodtime.gti.common.core.config.GtiConfig;
import com.mcgoodtime.gti.common.gxy.GXYMod;
import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.init.GtiItems;
import com.mcgoodtime.gti.common.init.Recipes;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
        modid = Gti.MOD_ID,
        name = Gti.MOD_NAME,
        version = Gti.VERSION,
        dependencies = "required-after:" + "Forge@[10.13.2.1230,);" + "after:" + "IC2@[2.2.660,);"
    )
public final class Gti {
    public static final String MOD_ID = "gti";
    public static final String MOD_NAME = "GoodTime-Industrial";
    public static final String VERSION = "Dev 0.0.5";
    public static final String RESOURCE_DOMAIN = "gti";
    public static final String GUI_PREFIX = "gui.gti.";

    @Instance(Gti.MOD_ID)
    public static Gti instance;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GtiConfig.init();
        GtiBlocks.init();
        GtiItems.init();
        Recipes.init();
        GXYMod.init();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, GuiHandler.getInstance());
        GameRegistry.registerTileEntity(TileCarbonizeFurnace.class, "TileEntityCarbonizeFurnace");
    }
}
