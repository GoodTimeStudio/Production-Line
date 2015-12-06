package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IBoxable;
import ic2.api.item.IElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by BestOwl on 2015.12.6.0006.
 *
 * @author BestOwl
 */
public class ItemCEU extends ItemGti implements IElectricItem, IBoxable {

    public int maxPowerTick;
    public int maxEnergy;
    public int tier;
    public IIcon[] icons;

    public ItemCEU() {
        super("ItemCEU");
        this.setMaxDamage(27);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);

        this.icons = new IIcon[5];
        this.tier = 1;
        this.maxEnergy = 20000;
        this.maxPowerTick = 32;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < 5; i++) {
            this.icons[i] = iconRegister.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "eustorage/itemCEU." + (i + 1));
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < 3) {
            return this.icons[0];
        }
        else if (meta < 10) {
            return this.icons[1];
        }
        else if (meta < 17) {
            return this.icons[2];
        }
        else if (meta < 24) {
            return this.icons[3];
        } else {
            return this.icons[4];
        }
    }

    @Override
    public boolean canBeStoredInToolbox(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxEnergy;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return this.maxPowerTick;
    }

    @SuppressWarnings({"NumericOverflow", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        ItemStack charged;
        if (this.getChargedItem(itemStack) == this) {
            charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 1.0D / 0.0, 2147483647, true, false);
            itemList.add(charged);
        }

        if (this.getEmptyItem(itemStack) == this) {
            charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 0.0D, 2147483647, true, false);
            itemList.add(charged);
        }
    }
}
