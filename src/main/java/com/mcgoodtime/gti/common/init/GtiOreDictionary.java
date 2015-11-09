package com.mcgoodtime.gti.common.init;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by BestOwl on 2015.11.7.0007.
 *
 * @author BestOwl
 */
public class GtiOreDictionary {

    public static void init() {
        OreDictionary.registerOre("paper", Items.paper);
        OreDictionary.registerOre("paper", GtiItems.rigidPaper);
    }
}
