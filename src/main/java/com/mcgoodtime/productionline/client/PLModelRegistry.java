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

import com.mcgoodtime.productionline.common.blocks.BlockMisc;
import com.mcgoodtime.productionline.common.blocks.IBlockType;
import com.mcgoodtime.productionline.common.blocks.IMultiIDBlock;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.items.ItemMulti;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Collection;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_ID;
import static com.mcgoodtime.productionline.common.core.ProductionLine.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2016/11/5.
 *
 * Register PL mods models.
 */
public class PLModelRegistry {

    public static void loadBlockModels() {
        registerBlockModel(PLBlocks.oreIridium);
        registerBlockModel(PLBlocks.airBrakeCasing);
        registerBlockModel(PLBlocks.compressedWaterHyacinth);
        registerBlockModel(PLBlocks.dehydratedWaterHyacinthblock);
        registerBlockModel(PLBlocks.carbonizeFurnace);
    }

    public static void loadItemModels() {
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

    private static ModelResourceLocation getItemModelResLoc(Item item, int meta) {
        String name = item.getRegistryName().getResourcePath();
        String path = "";
        String variant = "inventory";

        if (item instanceof IItemModelProvider) {
            path = ((IItemModelProvider) item).getModelResourcePath() + "/";

            String custom = ((IItemModelProvider) item).getModelResourceName(meta);
            if (custom != null) {
                name = custom;
            }
        }

        Block block = Block.getBlockFromItem(item);
        if (block instanceof IMultiIDBlock) {
            PropertyEnum propertyEnum = ((IMultiIDBlock) block).getBlockTypeContainer();
            Object object = propertyEnum.getAllowedValues().toArray()[meta];
            if (object instanceof IBlockType) {
                variant = propertyEnum.getName() + "=" +((IBlockType) object).getTypeName();
            }
        }

        ResourceLocation ret = new ResourceLocation(RESOURCE_DOMAIN, path + name);
        return new ModelResourceLocation(ret, variant);
    }

    private static ModelResourceLocation getBlockModelResLoc(Block block, int meta) {
        return getItemModelResLoc(Item.getItemFromBlock(block), meta);
    }

    //=========================
    //Block Model Registry

    private static void registerBlockModel(Block block, int metadata, ModelResourceLocation resourceLocation) {
        registerItemModel(Item.getItemFromBlock(block), metadata, resourceLocation);
    }

    private static void registerBlockModel(Block block) {
        registerBlockModel(block, 0, getBlockModelResLoc(block, 0));
    }

    private static void registerBlockModel(Block block, int metadata) {
        registerBlockModel(block, metadata, getBlockModelResLoc(block, metadata));
    }

    private static void registerBlockModel(ItemStack itemStack) {
        registerBlockModel(getBlockFromStack(itemStack), itemStack.getMetadata());
    }

    private static Block getBlockFromStack(ItemStack stackIn) {
        return Block.getBlockFromItem(stackIn.getItem());
    }

    //=========================



    //=========================
    //Item Model Registry

    private static void registerItemModel(Item item, int metadata, ModelResourceLocation resLoc) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, resLoc);
    }

    private static void registerItemModel(Item item, ModelResourceLocation resLoc) {
        registerItemModel(item, 0, resLoc);
    }

    private static void registerItemModel(Item item) {
        registerItemModel(item, getItemModelResLoc(item, 0));
    }

    private static void registerItemModel(Item item, int metadata) {
        registerItemModel(item, metadata, getItemModelResLoc(item, metadata));
    }

    //=========================

}
