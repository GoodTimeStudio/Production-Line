package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.init.GtiBlocks;
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
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;
    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;
    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;
    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    /** Holds a list of all registered achievements. */
    public static List achievementList = new ArrayList();

    /** Is the 'getting CarbonizeFurnace' achievement. */
    public static Achievement carbonizeFurnace = (new Achievement("achievement.CarbonizeFurnace", "CarbonizeFurnace", 0, 1, GtiBlocks.carbonizeFurnace, AchievementList.openInventory)).initIndependentStat().registerStat();

    /** Is the AchievementPage for Gti */
    public static AchievementPage pageGti = new AchievementPage("GoodTime Industrial", carbonizeFurnace);
    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}
}
