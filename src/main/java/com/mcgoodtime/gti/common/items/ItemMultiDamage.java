package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.Gti;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.List;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;
import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2015.11.5.0005.
 *
 * @author BestOwl
 */
public abstract class ItemMultiDamage extends ItemGti {

    protected IIcon[] icons;

    public ItemMultiDamage(String name) {
        super(name);
        int maxDamage = this.getMaxDamage();
        this.setMaxDamage(maxDamage);
        this.icons = new IIcon[maxDamage];
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < this.getMaxDamage(); i++) {
            this.icons[i] = iconRegister.registerIcon(RESOURCE_DOMAIN + ":" + this.getTextureFolder() + "item" + this.getInternalName(i));
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.icons[meta];
    }

    /**
     * Returns the maximum damage an item can take.
     */
    @Override
    public int getMaxDamage() {
        if (super.getMaxDamage() == 0) {
            int meta = 0;
            while (this.getInternalName(meta) != null) {
                meta++;
            }
            return meta;
        }
        else {
            return super.getMaxDamage();
        }
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item." + MOD_ID + "." + this.getInternalName(itemStack.getItemDamage());
    }

    public abstract String getInternalName(int meta);

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
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
     */
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }
}
