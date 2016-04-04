/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
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
package com.mcgoodtime.productionline.common.core;

import com.mcgoodtime.productionline.client.RenderEntityThrowable;
import com.mcgoodtime.productionline.client.RenderEntityRay;
import com.mcgoodtime.productionline.common.PLPotion;
import com.mcgoodtime.productionline.common.blocks.fluid.Gas;
import com.mcgoodtime.productionline.common.entity.EntityRay;
import com.mcgoodtime.productionline.common.entity.EntityThrowable;
import com.mcgoodtime.productionline.common.entity.PLEntity;
import com.mcgoodtime.productionline.common.event.PLEvent;
import com.mcgoodtime.productionline.common.init.*;
import com.mcgoodtime.productionline.common.nei.NEIPLConfig;
import com.mcgoodtime.productionline.common.worldgen.PLWorldGen;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;

@Mod(
        modid = ProductionLine.MOD_NAME,
        name = ProductionLine.MOD_NAME,
        version = ProductionLine.VERSION,
        dependencies = "required-after:"
                + "Forge@[10.13.4.1558,);"
                + "required-after:"
                + "IC2@[2.2.800,);"
                + "after:"
                + "NotEnoughItems;",
        useMetadata = true
    )
public final class ProductionLine {
    public static final String MOD_NAME = "ProductionLine";
    public static final String VERSION = "dev.0.3";
    public static final String RESOURCE_DOMAIN = "productionline";
    public static final String GUI_PREFIX = "gui.ProductionLine.";
    public static final CreativeTabs creativeTabGti = new CreativeTabs(MOD_NAME) {
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() {
            return PLItems.gravityRay;
        }
    };
    @Mod.Metadata
    public ModMetadata meta = new ModMetadata();
    @Instance(ProductionLine.MOD_NAME)
    public static ProductionLine instance;

    @SidedProxy(
            modId = MOD_NAME,
            serverSide = "com.mcgoodtime.productionline.common.core.ProductionLine$CommonProxy",
            clientSide = "com.mcgoodtime.productionline.common.core.ProductionLine$ClientProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        setupMeta();
        PLConfig.init(event.getSuggestedConfigurationFile());
        //register Blocks. 注册方块
        PLBlocks.init();
        FluidRegistry.registerFluid(Gas.gasNatural);
        //register Items. 注册物品
        PLItems.init();
        PLEntity.init();
        PLPotion.initPotion();
        proxy.init();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PLOreDictionary.init();
        PLEntity.init();
        // register Recipes. 注册合成
        PLRecipes.init();
        //register gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, GuiHandler.getInstance());
         //register achievement
        PLAchievement.init();
        //register achievement page
        AchievementPage.registerAchievementPage(PLAchievement.pagePL);
         //register ore gen bus. 注册矿石生成总线
        PLWorldGen.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //register Event. 注册事件
        FMLCommonHandler.instance().bus().register(new PLEvent());
        MinecraftForge.EVENT_BUS.register(new PLEvent());
    }

    private void setupMeta() {
        this.meta.modId = MOD_NAME;
        this.meta.name = MOD_NAME;
        this.meta.version = VERSION;
        this.meta.url = "https://github.com/GoodTimeStudio/Production-Line";
        this.meta.updateUrl = this.meta.url;
        this.meta.authorList = new ArrayList<String>();
        this.meta.authorList.add("BestOwl");
        this.meta.authorList.add("liach");
        this.meta.authorList.add("Seedking");
        this.meta.authorList.add("JAVA0");
        this.meta.authorList.add("GoodTime Studio");
        this.meta.credits = "GoodTime Studio";
    }

    public static class CommonProxy {
        public void init() {}
    }

    public static class ClientProxy extends CommonProxy {
        @Override
        public void init() {
            RenderingRegistry.registerEntityRenderingHandler(EntityThrowable.class, new RenderEntityThrowable());
            RenderingRegistry.registerEntityRenderingHandler(EntityRay.class, new RenderEntityRay());

            if (Loader.isModLoaded("NotEnoughItems")) {
                new NEIPLConfig().loadConfig();
            }
        }
    }
}
