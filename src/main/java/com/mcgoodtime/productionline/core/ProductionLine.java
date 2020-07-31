/*
 * This file is part of Production Line, licensed under MIT License (MIT).
 *
 * Copyright (c) 2020 GoodTime Studio <https://github.com/GoodTimeStudio>
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
package com.mcgoodtime.productionline.core;

import com.mcgoodtime.productionline.client.PLModelRegistry;
import com.mcgoodtime.productionline.client.RenderEntityRay;
import com.mcgoodtime.productionline.client.RenderEntityThrownItem;
import com.mcgoodtime.productionline.entity.EntityRay;
import com.mcgoodtime.productionline.entity.EntityThrownItem;
import com.mcgoodtime.productionline.entity.PLEntity;
import com.mcgoodtime.productionline.event.PLEvent;
import com.mcgoodtime.productionline.init.PLBlocks;
import com.mcgoodtime.productionline.init.PLItems;
import com.mcgoodtime.productionline.init.PLOreDictionary;
import com.mcgoodtime.productionline.potion.PLPotion;
import com.mcgoodtime.productionline.worldgen.PLWorldGen;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Arrays;

@Mod(
        modid = ProductionLine.MOD_ID,
        name = ProductionLine.MOD_NAME,
        version = ProductionLine.VERSION,
        dependencies = "required:forge@[14.23.5.2847,);",
        useMetadata = true
)
public final class ProductionLine {
    public static final String MOD_ID = "productionline";
    public static final String MOD_NAME = "ProductionLine";
    public static final String VERSION = "${version}";
    public static final String RESOURCE_DOMAIN = MOD_ID;
    public static final String GUI_PREFIX = "gui.ProductionLine.";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    public static final CreativeTabs creativeTabPL = new CreativeTabs(MOD_NAME) {
        @SideOnly(Side.CLIENT)
        @Override
        @Nonnull
        public ItemStack getTabIconItem() {
            return new ItemStack(PLItems.diamondApple);
        }
    };
    /**
     * Forge will inject to this field.
     */
    @Mod.Metadata
    private ModMetadata meta;
    private static final ProductionLine INSTANCE = new ProductionLine();

    public static boolean isIC2Loaded;
    public static boolean isBotaniaLoaded;

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        isIC2Loaded = Loader.isModLoaded("ic2");
        isBotaniaLoaded = Loader.isModLoaded("botania");
        setupMeta();
        PLConfig.init(event.getSuggestedConfigurationFile());
        PLEntity.init();
        proxy.preInit();
    }

    @SubscribeEvent
    public void registerBlock(RegistryEvent<Block> event) {
        PLBlocks.init();
    }

    @SubscribeEvent
    public void registerItem(RegistryEvent<Item> event) {
        //PLSounds.loadRecord(); //register record sound event before register record item.
        PLItems.init();
    }

    @SubscribeEvent
    public void registerModel(ModelRegistryEvent event)
    {
        PLModelRegistry.loadBlockModels();
        PLModelRegistry.loadItemModels();
    }

    @SubscribeEvent
    public void registerPotion(RegistryEvent<Potion> event) {
        PLPotion.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PLOreDictionary.init();
        //register gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, GuiHandler.getInstance());
        //register ore gen bus.
        PLWorldGen.init();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //register Event. 注册事件
        MinecraftForge.EVENT_BUS.register(new PLEvent());
    }

    private void setupMeta() {
        this.meta.modId = MOD_ID;
        this.meta.name = MOD_NAME;
        this.meta.version = VERSION;
        this.meta.url = "https://github.com/GoodTimeStudio/Production-Line";
        this.meta.authorList = Arrays.asList("BestOwl", "liach", "JAVA0", "Seedking", "GoodTime Studio");
        this.meta.credits = "GoodTime Studio";
    }

    public abstract static class CommonProxy {
        abstract void preInit();

        abstract void init();

        CommonProxy() {
        }
    }

    @SuppressWarnings("unused")
    @SideOnly(Side.SERVER)
    public static class ServerProxy extends CommonProxy {
        @Override
        void preInit() {
        }

        @Override
        void init() {
        }
    }

    @SuppressWarnings("unused")
    @SideOnly(Side.CLIENT)
    public static class ClientProxy extends CommonProxy {
        @Override
        void preInit() {
        }

        @Override
        void init() {
            RenderingRegistry.registerEntityRenderingHandler(EntityThrownItem.class, manager -> new RenderEntityThrownItem<>(manager, Minecraft.getMinecraft().getRenderItem()));
            RenderingRegistry.registerEntityRenderingHandler(EntityRay.class, RenderEntityRay::new);
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
