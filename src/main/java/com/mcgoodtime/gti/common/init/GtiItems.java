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
package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.items.ItemAirBrake;
import com.mcgoodtime.gti.common.items.ItemBambooCharcoal;
import com.mcgoodtime.gti.common.items.crushed.CrushedIridium;
import com.mcgoodtime.gti.common.items.crushed.cleaned.CleanedCrushedIridium;
import com.mcgoodtime.gti.common.items.dust.DustIridium;
import com.mcgoodtime.gti.common.items.food.DiamondApple;
import com.mcgoodtime.gti.common.items.ingot.IngotIridium;
import com.mcgoodtime.gti.common.items.plate.DenseDiamondPlate;
import com.mcgoodtime.gti.common.items.plate.DiamondPlate;
import com.mcgoodtime.gti.common.items.tools.IridiumPickaxe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;

/**
 * The list of all those items in GoodTime-Industrial.
 */
public class GtiItems {
    /* crushed ======================== */
    public static Item crushedIridium;

    public static Item cleanedCrushedIridium;
     /* =============================== */

    /* dust ========================= */
    public static Item dustIridium;
    public static Item smallDustIridium;

    /* ============================== */

    /* ingot ======================== */
    public static Item ingotIridium;
    /* ============================== */

    /* plate ===================== */
    public static Item denseDiamondPlate;
    public static Item diamondPlate;
    public static ItemPickaxe iridiumPickaxe;
    /* =========================== */

    public static ItemFood diamondApple;
    public static Item airBrakeUnit;
    public static Item airBrakeCasing;
    public static Item bambooCharcoal;

    public static void init() {
        diamondApple = new DiamondApple();
        diamondPlate = new DiamondPlate();
        denseDiamondPlate = new DenseDiamondPlate();
        crushedIridium = new CrushedIridium();
        cleanedCrushedIridium = new CleanedCrushedIridium();
        dustIridium = new DustIridium();
        smallDustIridium = new DustIridium.DustIridiumSmall();
        iridiumPickaxe = new IridiumPickaxe();
        ingotIridium = new IngotIridium();
        airBrakeUnit = new ItemAirBrake.ItemAirBrakeUnit();
        airBrakeCasing = new ItemAirBrake.ItemAirBrakeCasing();
        bambooCharcoal = new ItemBambooCharcoal();
    }
}
