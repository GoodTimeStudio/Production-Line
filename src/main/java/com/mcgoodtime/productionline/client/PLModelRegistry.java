/*
 * This file is part of ProductionLine, licensed under MIT License (MIT).
 *
 * Copyright (c) 2016 GoodTime Studio <https://github.com/GoodTimeStudio>
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

package com.mcgoodtime.productionline.client;

import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.items.ItemMulti;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static com.mcgoodtime.productionline.common.core.ProductionLine.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2016/11/5.
 *
 * Register PL mods models.
 */
public class PLModelRegistry {

    public static void registerItemModel(Item item, int metadata, ModelResourceLocation resLoc) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, resLoc);
    }

    public static void registerItemModel(Item item, ModelResourceLocation resLoc) {
        registerItemModel(item, 0, resLoc);
    }

    public static void registerItemModel(Item item) {
        registerItemModel(item, getItemModelResLoc(item, 0));
    }

    public static void registerItemModel(Item item, int metadata) {
        registerItemModel(item, metadata, getItemModelResLoc(item, metadata));
    }

    public static ModelResourceLocation getItemModelResLoc(Item item, int meta) {
        String name = item.getRegistryName().getResourcePath();
        String path = "";
        if (item instanceof IItemModelProvider) {
            path = ((IItemModelProvider) item).getModelResourcePath() + "/";

            String custom = ((IItemModelProvider) item).getModelResourceName(meta);
            if (custom != null) {
                name = custom;
            }
        }
        ResourceLocation ret = new ResourceLocation(RESOURCE_DOMAIN, path + name);
        return new ModelResourceLocation(ret, "normal");
    }

    static {
        registerItemModel(PLItems.diamondApple, 0);
        registerItemModel(PLItems.diamondApple, 1);
        registerItemModel(PLItems.ironTreetap);
        registerItemModel(PLItems.bronzeTreetap);
        registerItemModel(PLItems.leadTreetap);
        registerItemModel(PLItems.refinedIronTreetap);
        registerItemModel(PLItems.advancedAlloyTreetap);
        registerItemModel(PLItems.carbonTreetap);
        registerItemModel(PLItems.record_MusicSpring);
        registerItemModel(PLItems.salt);
        registerItemModel(PLItems.ceu);
        registerItemModel(PLItems.gravityRay);
        registerItemModel(PLItems.packagedSalt);
        registerItemModel(PLItems.saltWaterBucket);

        if (PLItems.itemCrafting instanceof ItemMulti) {
            for (int i = 0; i < ((ItemMulti) PLItems.itemCrafting).getInternalNameSize(); i++) {
                registerItemModel(PLItems.itemCrafting, i);
            }
        }

        if (PLItems.itemOre instanceof ItemMulti) {
            for (int i = 0; i < ((ItemMulti) PLItems.itemOre).getInternalNameSize(); i++) {
                registerItemModel(PLItems.itemOre, i);
            }
        }
    }
}
