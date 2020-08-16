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

import com.mcgoodtime.productionline.items.ItemSeal;
import com.mcgoodtime.productionline.items.ItemCrafting;
import com.mcgoodtime.productionline.items.ItemDiamondApple;
import com.mcgoodtime.productionline.items.ItemOre;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


/**
 * The list of all those items in Production_Line.
 */
public class PLItems {

    public static Item diamondApple;

    //tool
    public static Item sheepCrook;

    //ore
    public static Item itemOre;
    public static ItemStack ingotIridium;

    //crafting
    public static Item itemCrafting;
    public static ItemStack aquamarine;
    public static ItemStack eyeOfTheDesert;

    //seal
    public static Item itemSeal;

    public static Item cconvertingSealBlackCat;

    public static ItemStack enchantingSealSandStorm;
    public static ItemStack enchantingSealSpring;
    public static ItemStack enchantingSealOasis;
    public static ItemStack enchantingSealAnotherWorld;

    public static void init() {
        diamondApple = new ItemDiamondApple();

        //MultiMetaItem registry
        itemOre = new ItemOre();
        itemCrafting = new ItemCrafting();
        itemSeal = new ItemSeal();
    }

}
