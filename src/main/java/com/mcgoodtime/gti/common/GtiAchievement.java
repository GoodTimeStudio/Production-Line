package com.mcgoodtime.gti.common;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.JsonSerializableSet;
import net.minecraftforge.common.AchievementPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhao on 2015/5/17.
 */
public class GtiAchievement {
    /** Is the 'ir ore' achievement. */
    public static Achievement getIrOre = (new Achievement("achievement.getIrOre", "getIrOre", 0, 0,
            GtiBlocks.oreIridium, AchievementList.diamonds)).registerStat();

    /** Is the 'getting CarbonizeFurnace' achievement. */
    public static Achievement getCarbonizeFurnace = (new Achievement("achievement.getCarbonizeFurnace",
            "getCarbonizeFurnace", 0, 1, GtiBlocks.carbonizeFurnace, AchievementList.buildFurnace)).registerStat();

    /** Is the AchievementPage for Gti */
    public static AchievementPage pageGti = new AchievementPage("GoodTime Industrial",
            new Achievement[]{
                    getIrOre,
                    getCarbonizeFurnace
            });

    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {

    }
}
