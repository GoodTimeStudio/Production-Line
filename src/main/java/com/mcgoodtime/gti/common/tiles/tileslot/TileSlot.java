package com.mcgoodtime.gti.common.tiles.tileslot;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BestOwl on 2015.11.9.0009.
 *
 * @author BestOwl
 */
public class TileSlot {

    public final SlotMode slotMode;

    public final TileContainer tile;
    private ItemStack item;

    public TileSlot(TileContainer tile, SlotMode mode) {
        this.tile = tile;
        this.slotMode = mode;
    }

    public void putStack(ItemStack itemStack) {
        this.item = itemStack;
    }

    public ItemStack getStack() {
        return this.item;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        if (this.item != null) {
            this.item.writeToNBT(nbtTagCompound);
        }
        nbt.setTag("TileSlot", nbtTagCompound);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.item = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("TileSlot"));
    }

    /**
     * Whether the current item can be inputted.
     * @param itemStack Input item.
     */
    public boolean canInput(ItemStack itemStack) {
        return true;
    }

    public enum SlotMode {
        INPUT,
        OUTPUT,
        INOUT,
        NULL
    }
}
