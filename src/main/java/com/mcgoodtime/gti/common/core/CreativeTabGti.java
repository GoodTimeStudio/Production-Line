package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.items.Food;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabGti extends CreativeTabs {
    private ItemStack display;

    public CreativeTabGti() {
        super("GoodTime Industrial");
    }

    public Item getTabIconItem(){
        return Food.DimApple;
    }

    public ItemStack getIconItemStack() {
        if (display == null) {
            display = new ItemStack(Food.DimApple).copy();
        }
        return display;
    }
}
