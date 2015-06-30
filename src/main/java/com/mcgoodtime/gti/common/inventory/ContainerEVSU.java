package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileEVSU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by suhao on 2015-6-10-0010.
 */
public class ContainerEVSU extends Container {

    private TileEVSU tileEVSU;

    public ContainerEVSU(InventoryPlayer playerInv, TileEVSU evsu) {
        this.tileEVSU = evsu;
        this.addSlotToContainer(new Slot(evsu, 0, 56, 17));
        this.addSlotToContainer(new Slot(evsu, 1, 56, 53));
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

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
