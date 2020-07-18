package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.client.IItemModelProvider;
import com.mcgoodtime.productionline.core.ProductionLine;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static com.mcgoodtime.productionline.core.ProductionLine.MOD_ID;

/**
 * Created by BestOwl on 2015.11.5.0005.
 *
 * @author BestOwl
 */
public abstract class ItemMulti extends ItemPL implements IItemModelProvider {

    private int meta = 0;

    protected List<String> internalNameList = this.getInternalNameList();

    public ItemMulti(String name) {
        super(name);
        this.setHasSubtypes(true);
    }

    protected ItemStack next() {
        return new ItemStack(this, 1, this.meta++);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (internalNameList.size() > 0) {
            return "item." + MOD_ID + "." + this.getInternalName(itemStack.getItemDamage());
        } else {
            return super.getUnlocalizedName(itemStack);
        }
    }

    public String getInternalName(int meta) {
        return this.internalNameList.get(meta);
    }

    public int getInternalNameSize() {
        return this.internalNameList.size();
    }

    protected abstract List<String> getInternalNameList();

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == ProductionLine.creativeTabPL)
        {
            for(int meta = 0; meta < this.internalNameList.size(); ++meta) {
                ItemStack stack = new ItemStack(this, 1, meta);
                items.add(stack);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int i = 1;
        String unLocal = this.getUnlocalizedName(stack) + ".desc" + i;

        while (I18n.hasKey(unLocal)) {
            tooltip.add(I18n.format(unLocal));
            i++;
            unLocal = this.getUnlocalizedName() + ".desc" + i;
        }
    }

    /**
     * Get custom resource name.
     * To use default resource name, return null.
     */
    @Override
    public String getModelResourceName(int meta) {
        return this.internalNameList.get(meta);
    }
}