package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.items.Food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * The class of GoodTime-Industrial's creative tab.
 *
 * @author liach
 */
public class CreativeTabGti extends CreativeTabs {

    /** The CreativeTab. */
    public static final CreativeTabGti creativeTabGti = new CreativeTabGti();

    private ItemStack display;

    private CreativeTabGti() {
        super("GoodTime Industrial");
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem(){
        return null;
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (display == null) {
            display = new ItemStack(Food.DimApple).copy();
        }
        return display;
    }
}
