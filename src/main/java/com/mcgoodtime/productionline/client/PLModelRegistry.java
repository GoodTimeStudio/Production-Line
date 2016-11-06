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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

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
        registerItemModel(item, getItemModelResLoc(item));
    }

    public static void registerItemModel(Item item, int metadata) {
        registerItemModel(item, metadata, getItemModelResLoc(item));
    }

    public static ModelResourceLocation getItemModelResLoc(Item item) {
        ResourceLocation tmp = item.getRegistryName();
        ResourceLocation ret = new ResourceLocation(tmp.getResourceDomain(), tmp.getResourcePath().replace('.', '/'));
        return new ModelResourceLocation(ret, "normal");
    }

    static {
        registerItemModel(PLItems.diamondApple, 0);
        registerItemModel(PLItems.diamondApple, 1);
    }
}
