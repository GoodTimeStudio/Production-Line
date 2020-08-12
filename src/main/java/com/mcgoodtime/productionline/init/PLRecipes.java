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
package com.mcgoodtime.productionline.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;
import java.util.Map;

import ic2.api.item.IC2Items;
import net.minecraftforge.oredict.OreDictionary;


/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class PLRecipes {
    private static final float XP = 2F;

    /**
     * Load recipes of GoodTime-Industrial.
     */
    public static void init() {
        //smelting registry
        GameRegistry.addSmelting(PLBlocks.oreIridium, PLItems.ingotIridium, XP);
        for(ItemStack oreIridium:OreDictionary.getOres("oreIridium")){
            GameRegistry.addSmelting(oreIridium, PLItems.ingotIridium, XP);
        }
    }

    /**
     * Disable recipes.
     *
     * @param itemStack Disable all recipes of this item.
     */
    @SuppressWarnings("unchecked")
    public static void disableRecipes(ItemStack itemStack) {

    }

    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public static void disableSmelting(ItemStack itemStack) {
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.instance().getSmeltingList();
        smelting.remove(itemStack);
    }
}
