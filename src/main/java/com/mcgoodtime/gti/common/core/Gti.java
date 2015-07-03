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

import com.mcgoodtime.gti.common.init.*;
import com.mcgoodtime.gti.common.blocks.fluid.Gas;

import com.mcgoodtime.gti.common.worldgen.IridiumGen;
import com.mcgoodtime.gti.common.worldgen.WorldGenerationHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;

@Mod(
        modid = Gti.MOD_ID,
        name = Gti.MOD_NAME,
        version = Gti.VERSION,
        dependencies = "required-after:"
                + "Forge@[10.13.2.1291,);"
                + "required-after:"
                + "IC2@[2.2.660,);",
        useMetadata = true
    )
public final class Gti {
    public static final String MOD_ID = "gti";
    public static final String MOD_NAME = "GoodTime-Industrial";
    public static final String VERSION = "Dev.0.1";
    public static final String RESOURCE_DOMAIN = "gti";
    public static final String GUI_PREFIX = "gui.gti.";
    public static final CreativeTabs creativeTabGti = new CreativeTabs(MOD_NAME) {
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() {
            return GtiItems.diamondApple;
        }
    };
    @Mod.Metadata
    public ModMetadata meta = new ModMetadata();
    @Instance(Gti.MOD_ID)
    public static Gti instance;
   
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        setupMeta();
        FMLCommonHandler.instance().bus().register(new GtiEvent());
        GtiConfig.configFile = event.getSuggestedConfigurationFile();
        GtiConfig.init();
        GtiBlocks.init(); //register blocks
        FluidRegistry.registerFluid(Gas.gasNatural);
        GtiItems.init(); //register items
        GtiTiles.init();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Recipes.init(); //register recipes
         //register TileEntity
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, GuiHandler.getInstance()); //register gui handler
         //register achievement
        GtiAchievement.init();
        AchievementPage.registerAchievementPage(GtiAchievement.pageGti); //register achievement page
         //register ore gen bus
        WorldGenerationHandler.init();
    }

    private void setupMeta() {
        this.meta.modId = MOD_ID;
        this.meta.name = MOD_NAME;
        this.meta.version = "dev 0.0.6";
        this.meta.url = "https://github.com/Minecraft-GoodTime/GoodTime-Industrial";
        this.meta.updateUrl = this.meta.url;
        this.meta.authorList.add("_JAVA7");
        this.meta.authorList.add("liach");
        this.meta.authorList.add("GoodTime Studio");
        this.meta.credits = "GoodTime Studio";
    }
}
