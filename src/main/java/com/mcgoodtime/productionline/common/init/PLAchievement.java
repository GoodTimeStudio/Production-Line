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
package com.mcgoodtime.productionline.common.init;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

/*
 * Created by suhao on 2015/5/17.
 */

public class PLAchievement {
    /** Is the 'ir ore' achievement. */
    public static Achievement getIrOre = new Achievement("achievement.getIrOre", "getIrOre", 0, 0,
            PLBlocks.oreIridium, AchievementList.ACQUIRE_IRON).registerStat();
    /** Just for funny, :) */
//    public static Achievement yourHouseBombed = new Achievement("achievement.yourHouseBombed", "yourHouseBombed", 0, 3,
//            PLItems.yourHouseBombed, null).setSpecial().registerStat().initIndependentStat();

    /** Is the 'getting CarbonizeFurnace' achievement. */
    public static Achievement getCarbonizeFurnace = new Achievement(
            "achievement.getCarbonizeFurnace",
            "getCarbonizeFurnace", 0, 1, PLBlocks.carbonizeFurnace, AchievementList.BUILD_FURNACE
    ).registerStat();

    /** Is the AchievementPage for ProductionLine */
    public static AchievementPage pagePL = new AchievementPage(
            ProductionLine.MOD_NAME,
            getIrOre,
            getCarbonizeFurnace/*,
            yourHouseBombed*/);

    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {
    }
}
