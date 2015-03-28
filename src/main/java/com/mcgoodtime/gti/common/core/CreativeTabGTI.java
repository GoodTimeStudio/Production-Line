package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.Items.Food;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabGTI extends CreativeTabs {

    public CreativeTabGTI(String lable) {
        super(lable);
    }

    @Override
    public Item getTabIconItem() {
        return Food.DimApple;
    }
}
