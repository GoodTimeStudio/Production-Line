package com.mcgoodtime.gti.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileGenGasKu extends TileEntity implements ISidedInventory {

    private ItemStack GenGasKUStack[] = new ItemStack[3];

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * blocks.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return 0;
    }

    /**
     * Returns the stack in slot i
     * 取得槽（库存）中的物品栈
     */
    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return GenGasKUStack[p_70301_1_];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     * 玩家取走物品时
     */
    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        if (this.GenGasKUStack[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.GenGasKUStack[p_70298_1_].stackSize <= p_70298_2_)
            {
                itemstack = this.GenGasKUStack[p_70298_1_];
                this.GenGasKUStack[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.GenGasKUStack[p_70298_1_].splitStack(p_70298_2_);

                if (this.GenGasKUStack[p_70298_1_].stackSize == 0)
                {
                    this.GenGasKUStack[p_70298_1_] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     * 设置槽（库存）中的内容（物品栈），
     */
    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        this.GenGasKUStack[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit())
        {
            p_70299_2_.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName() {
        return new String("container.GenGasKU");
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     * 物品栈最大堆叠
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     * 使它的gui默认能被任何人打开
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param p_94041_1_
     * @param p_94041_2_
     */
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}