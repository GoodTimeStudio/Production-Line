package com.mcgoodtime.productionline.common.items;

import ic2.api.energy.EnergyNet;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by BestOwl on 2015.12.8.0008.
 *
 * @author BestOwl
 */
public class ItemElectricPL extends ItemPL implements IElectricItem {

    protected int maxPowerTick;
    protected int maxEnergy;
    protected int tier;

    public ItemElectricPL(String name, int tier, int maxEnergy) {
        super(name);
        this.setMaxDamage(27);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.tier = tier;
        this.maxEnergy = maxEnergy;
        this.maxPowerTick = (int) EnergyNet.instance.getPowerFromTier(tier);
        this.addPropertyOverrides();
    }

    protected void addPropertyOverrides() {
        this.addPropertyOverride(new ResourceLocation("energy"), (stack, worldIn, entityIn) -> {
            int meta = stack.getMetadata();
            if (meta > 24) {
                return 1;
            }
            else if (meta > 17) {
                return 2;
            }
            else if (meta > 10) {
                return 3;
            }
            else if (meta > 3) {
                return 4;
            }
            else {
                return 0;
            }
        });
    }

    @SuppressWarnings({"NumericOverflow", "unchecked"})
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        ElectricItem.manager.charge(itemStack, 0.0D, Integer.MAX_VALUE, true, false);
        itemList.add(itemStack);

        ItemStack charged = new ItemStack(this, 1);
        ElectricItem.manager.charge(charged, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, true, false);
        itemList.add(charged);
    }

    /**
     * Determine if the item can be used in a machine or as an armor part to supply energy.
     *
     * @return Whether the item can supply energy
     */
    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    /**
     * Get the item's maximum charge energy in EU.
     *
     * @return Maximum charge energy
     */
    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxEnergy;
    }

    /**
     * Get the item's tier, lower tiers can't send energy to higher ones.
     * Batteries are Tier 1, Energy Crystals are Tier 2, Lapotron Crystals are Tier 3.
     *
     * @return Item's tier
     */
    @Override
    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    /**
     * Get the item's transfer limit in EU per transfer operation.
     *
     * @return Transfer limit
     */
    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return this.maxPowerTick;
    }

}
