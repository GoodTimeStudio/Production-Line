package com.mcgoodtime.gti.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by BestOwl on 2015.10.31.0031.
 *
 * Gti base container.
 * @author BestOwl
 */
public abstract class ContainerGti<T extends IInventory> extends Container {

    public T tile;

    public ContainerGti(EntityPlayer player, T tile) {
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    public T getTileEntity() {
        return this.tile;
    }
}
