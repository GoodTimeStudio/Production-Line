/*
package com.mcgoodtime.productionline.common.items;

import net.minecraftforge.fml.common.registry.GameData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.12.4.0004.
 *
 * @author BestOwl
 *//*
public class ItemDryFood extends ItemPL {

    public List<ItemFood> foodList = new ArrayList<ItemFood>();

    public ItemDryFood() {
        super("ItemDryFood");
        int maxDamage = this.getMaxDamage();
        this.setMaxDamage(maxDamage);
        this.setHasSubtypes(true);
        this.setMaxStackSize(128);

        for (Object item : (Iterable) GameData.getItemRegistry()) {
            if (item instanceof ItemFood) {
                this.foodList.add((ItemFood) item);
            }
        }
    }

    /**
     * Returns the maximum damage an item can take.
     *//*
    @Override
    public int getMaxDamage() {
        return this.foodList.size();
    }

    /**
     * Gets an icon index based on an item's damage value
     *//*
    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.foodList.get(meta).getIconFromDamage(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        super.addInformation(itemStack, player, list, bool);
        int meta = itemStack.getItemDamage();
        list.add(this.foodList.get(meta).getItemStackDisplayName(itemStack));
    }

    //----------------------

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     *//*
    @Override
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for(int meta = 0; meta < this.getMaxDamage(); ++meta) {
            ItemStack stack = new ItemStack(this, 1, meta);
            list.add(stack);
        }
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    /**
     * Determines if the durability bar should be rendered for this item.
     * Defaults to vanilla stack.isDamaged behavior.
     * But modders can use this for any data they wish.
     *
     * @param stack The current Item Stack
     * @return True if it should render the 'durability' bar.
     *//*
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
*/