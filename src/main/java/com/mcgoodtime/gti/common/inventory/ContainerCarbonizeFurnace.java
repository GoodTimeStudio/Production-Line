package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * @author suhao
 */
public class ContainerCarbonizeFurnace extends Container {
    private TileCarbonizeFurnace tileFurnace;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCarbonizeFurnace(InventoryPlayer playerInv, TileCarbonizeFurnace carbonizeFurnace) {
        this.tileFurnace = carbonizeFurnace;
        this.addSlotToContainer(new Slot(carbonizeFurnace, 0, 56, 17));
        this.addSlotToContainer(new Slot(carbonizeFurnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(playerInv.player, carbonizeFurnace, 2, 116, 35));

        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting crafters) {
        super.addCraftingToCrafters(crafters);
        crafters.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
        crafters.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
        crafters.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileFurnace.furnaceCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.furnaceCookTime);
            }

            if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileFurnace.furnaceCookTime;
        this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
        this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int type, int value) {
        if (type == 0) {
            this.tileFurnace.furnaceCookTime = value;
        }

        if (type == 1) {
            this.tileFurnace.furnaceBurnTime = value;
        }

        if (type == 2) {
            this.tileFurnace.currentItemBurnTime = value;
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.tileFurnace.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     * 
     * Note: This method needs a rewrite. 
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNumber == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotNumber != 1 && slotNumber != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (slotNumber >= 3 && slotNumber < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (slotNumber >= 30 && slotNumber < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
