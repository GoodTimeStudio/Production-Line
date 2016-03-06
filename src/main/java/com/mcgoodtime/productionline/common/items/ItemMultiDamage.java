package com.mcgoodtime.productionline.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import java.util.List;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_NAME;
import static com.mcgoodtime.productionline.common.core.ProductionLine.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2015.11.5.0005.
 *
 * @author BestOwl
 */
public abstract class ItemMultiDamage extends ItemPL {

    protected List<String> internalNameList = this.getInternalNameList();
    protected IIcon[] icons;

    public ItemMultiDamage(String name) {
        super(name);
        int maxDamage = this.getMaxDamage();
        this.setMaxDamage(maxDamage);
        this.setHasSubtypes(true);
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
        return this.internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item." + MOD_NAME + "." + this.getInternalName(itemStack.getItemDamage());
    }

    public String getInternalName(int meta) {
        return this.internalNameList.get(meta);
    }

    protected abstract List<String> getInternalNameList();

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

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        int i = 1;
        String unLocal = this.getUnlocalizedName(itemStack) + ".desc" + i;

        while (StatCollector.canTranslate(unLocal)) {
            list.add(StatCollector.translateToLocal(unLocal));
            i++;
            unLocal = this.getUnlocalizedName() + ".desc" + i;
        }
    }
}
