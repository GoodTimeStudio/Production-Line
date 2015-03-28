package com.mcgoodtime.gti.common.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGenGasKU extends TileEntity implements ISidedInventory {

    private ItemStack stack[] = new ItemStack[3];

    @Override
    public void updateEntity() {
        //System.out.println("Test TileEntity");
    }

    @Override
    public int getSizeInventory() {
        return stack.length;
    }

    //获取库存物品栈
    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return stack[p_70301_1_];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.stack[par1] != null)
        {
            ItemStack itemstack;

            if (this.stack[par1].stackSize <= par2)
            {
                itemstack = this.stack[par1];
                this.stack[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.stack[par1].splitStack(par2);

                if (this.stack[par1].stackSize == 0)
                {
                    this.stack[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    //关闭时调用
    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    //设置库存中的内容（物品栈）
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.stack[var1] = var2;
        if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    //获取Inv名称
    @Override
    public String getInventoryName() {
        return new String ("container.GenGasKU");
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    //获取库存单个物品最大堆叠
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

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

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return true;
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }
}
