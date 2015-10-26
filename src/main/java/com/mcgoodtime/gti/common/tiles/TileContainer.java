package com.mcgoodtime.gti.common.tiles;

import ic2.api.item.IElectricItem;
import ic2.core.item.BaseElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhao on 2015.10.25.0025.
 *
 * @author BestOwl
 */
public abstract class TileContainer extends TileGti implements ISidedInventory {

    /** The ItemStacks that hold the itemsList in the container */
    public List<ItemStack> containerItemsList = new ArrayList<ItemStack>();
    protected String name;

    public TileContainer() {
        for (int i = 0; i < getSizeInventory(); i++) {
            containerItemsList.add(null);
        }
    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public abstract String getInventoryName();

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation
     * on the given side of this block.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        int[] ret = new int[this.getSizeInventory()];
        for (int i = 0; i < ret.length; i++){
            ret[i] = i;
        }
        return ret;
    }

    /**
     * Returns true if automation can insert the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
    }


    /**
     * Returns the stack in slot
     * @param slot The number of slot
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.containerItemsList.get(slot);
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public abstract ItemStack decrStackSize(int slot, int num);

    /**
     * When some containers are closed they call this on each slot, then drop whatever
     * it returns as an EntityItem like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemstack = this.containerItemsList.get(slot);
        if (itemstack != null) {
            this.containerItemsList.set(slot, null);
        }
        return itemstack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory
     * (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.containerItemsList.set(slot, itemStack);
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * @return if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName() {
        return this.name != null && this.name.length() > 0;
    }

    /**
     * @return the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return !this.isInvalid() && entityPlayer.getDistance((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    /**
     * Return true if automation is allowed to insert the given
     * stack (ignoring stack size) into the given slot.
     * @param slot The number of slot.
     */
    @Override
    public abstract boolean isItemValidForSlot(int slot, ItemStack itemStack);

    /**
     *
     * @return Whether the item can supply energy
     */
    public static boolean isElectricPower(ItemStack item) {
        if (item.getItem() instanceof BaseElectricItem) {
            ((BaseElectricItem) item.getItem()).canProvideEnergy(item);
            return true;
        }
        return false;
    }
}
