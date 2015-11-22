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
package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.core.Gti;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

/*
 * Created by suhao on 2015/5/17.
 */

public class GtiAchievement {
    /** Is the 'ir ore' achievement. */
    public static Achievement getIrOre = new Achievement("achievement.getIrOre", "getIrOre", 0, 0,
            GtiBlocks.oreIridium, AchievementList.acquireIron).registerStat();

    /** Is the 'getting CarbonizeFurnace' achievement. */
    public static Achievement getCarbonizeFurnace = new Achievement(
            "achievement.getCarbonizeFurnace",
            "getCarbonizeFurnace", 0, 1, GtiBlocks.carbonizeFurnace, AchievementList.buildFurnace
    ).registerStat();

    /** Is the AchievementPage for Gti */
    public static AchievementPage pageGti = new AchievementPage(
            Gti.MOD_NAME,
            getIrOre,
            getCarbonizeFurnace);

    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {
    }
}
