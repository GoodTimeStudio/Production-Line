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

//import com.mcgoodtime.productionline.client.RenderEntityThrowable;
//import com.mcgoodtime.productionline.client.RenderEntityRay;
//import com.mcgoodtime.productionline.common.potion.PLPotion;
//import com.mcgoodtime.productionline.common.blocks.fluid.Gas;
//import com.mcgoodtime.productionline.common.entity.EntityThrowable;
//import com.mcgoodtime.productionline.common.entity.PLEntity;
//import com.mcgoodtime.productionline.common.event.PLEvent;
//import com.mcgoodtime.productionline.common.init.*;
//import com.mcgoodtime.productionline.common.nei.NEIPLConfig;
//import com.mcgoodtime.productionline.common.worldgen.PLWorldGen;

import com.mcgoodtime.productionline.common.entity.PLEntity;
import com.mcgoodtime.productionline.common.event.PLEvent;
import com.mcgoodtime.productionline.common.init.PLAchievement;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.init.PLOreDictionary;
import com.mcgoodtime.productionline.common.init.PLRecipes;
import com.mcgoodtime.productionline.common.potion.PLPotion;
import com.mcgoodtime.productionline.common.worldgen.PLWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Arrays;

@Mod(
        modid = ProductionLine.MOD_ID,
        name = ProductionLine.MOD_NAME,
        version = ProductionLine.VERSION,
        dependencies = "required-after:"
                + "Forge@[12.17.0.1976,);"
                + "required-after:"
                + "IC2@[2.5.52,);",
        useMetadata = true
)
public final class ProductionLine {
    public static final String MOD_ID = "productionline";
    public static final String MOD_NAME = "ProductionLine";
    public static final String VERSION = "${version}";
    public static final String RESOURCE_DOMAIN = MOD_ID;
    public static final String GUI_PREFIX = "gui.ProductionLine.";
    public static final CreativeTabs creativeTabGti = new CreativeTabs(MOD_NAME) {
        @SideOnly(Side.CLIENT)
        @Override
        @Nonnull
        public Item getTabIconItem() {
            return Items.GOLDEN_APPLE;
        }
    };
    /**
     * Forge will inject to this field.
     */
    @Mod.Metadata
    private ModMetadata meta;
    private static final ProductionLine INSTANCE = new ProductionLine();

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        setupMeta();
        PLConfig.init(event.getSuggestedConfigurationFile());
        //register Blocks. 注册方块
        PLBlocks.init();
//        FluidRegistry.registerFluid(Gas.gasNatural);
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
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, GuiHandler.getInstance());
        //register achievement
        PLAchievement.init();
        //register achievement page
        AchievementPage.registerAchievementPage(PLAchievement.pagePL);
        //register ore gen bus. 注册矿石生成总线
        PLWorldGen.init();
    }

    private void setupMeta() {
        this.meta.modId = MOD_ID;
        this.meta.name = MOD_NAME;
        this.meta.version = VERSION;
        this.meta.url = "https://github.com/GoodTimeStudio/Production-Line";
        this.meta.authorList = Arrays.asList("BestOwl", "liach", "JAVA0", "Seedking", "GoodTime Studio");
        this.meta.credits = "GoodTime Studio";
    }

    public static class CommonProxy {
        public void init() {
        }

        public void registerItemRender(Item item) {

        }
    }

    public static class ServerProxy extends CommonProxy {
    }

    @SideOnly(Side.CLIENT)
    public static class ClientProxy extends CommonProxy {
        @Override
        public void init() {
//            ModelLoaderRegistry.registerLoader(new PLItemModelLoader());
//            RenderingRegistry.registerEntityRenderingHandler(EntityThrowable.class, new RenderEntityThrowable());
//            RenderingRegistry.registerEntityRenderingHandler(EntityRay.class, new RenderEntityRay());
//
//            if (Loader.isModLoaded("NotEnoughItems")) {
//                new NEIPLConfig().loadConfig();
//            }
        }

        @Override
        public void registerItemRender(Item item) {
        }
    }

    @Mod.InstanceFactory
    public static ProductionLine getInstance() {
        return INSTANCE;
    }

    public static ResourceLocation loc(String name) {
        return new ResourceLocation(ProductionLine.MOD_ID, name);
    }
}
