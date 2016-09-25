/*
package com.mcgoodtime.productionline.common.inventory;

import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 *//*
public class ContainerAdvSolar extends ContainerPL<TileAdvSolar> {

    public boolean sunIsVisible;
    public boolean hasLens;

    public ContainerAdvSolar(EntityPlayer player, TileAdvSolar tile) {
        super(player, tile);
        this.addSlotToContainer(new Slot(this.tile, 0, 61, 26));
        this.addSlotToContainer(new Slot(this.tile, 1, 99, 26));
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.sunIsVisible ? 1 : 0);
        iCrafting.sendProgressBarUpdate(this, 1, this.hasLens ? 1 : 0);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     *//*
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object object : this.crafters) {
            if (this.sunIsVisible != this.tile.sunIsVisible) {
                ((ICrafting) object).sendProgressBarUpdate(this, 0, this.tile.sunIsVisible ? 1 : 0);
            }
            if (this.hasLens != this.tile.hasLens) {
                ((ICrafting) object).sendProgressBarUpdate(this, 1, this.tile.hasLens ? 1 : 0);
            }
        }

        this.sunIsVisible = this.tile.sunIsVisible;
        this.hasLens = this.tile.hasLens;
    }

    @Override
    public void updateProgressBar(int id, int var) {
        super.updateProgressBar(id, var);

        switch (id) {
            case 0: {
                switch (var) {
                    case 1:
                        this.tile.sunIsVisible = true;
                        break;
                    case 0:
                        this.tile.sunIsVisible = false;
                }
                break;
            }
            case 1: {
                switch (var) {
                    case 1:
                        this.tile.hasLens = true;
                        break;
                    case 0:
                        this.tile.hasLens = false;
                }
                break;
            }
        }
    }
}*/
