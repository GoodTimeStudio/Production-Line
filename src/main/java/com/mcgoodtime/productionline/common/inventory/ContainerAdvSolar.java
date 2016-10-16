package com.mcgoodtime.productionline.common.inventory;

import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
public class ContainerAdvSolar extends ContainerPL<TileAdvSolar> {

    public boolean sunIsVisible;
    public boolean hasLens;

    public ContainerAdvSolar(EntityPlayer player, TileAdvSolar tile) {
        super(player, tile);
        this.addSlotToContainer(new Slot(this.tile, 0, 61, 26));
        this.addSlotToContainer(new Slot(this.tile, 1, 99, 26));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendProgressBarUpdate(this, 0, this.sunIsVisible ? 1 : 0);
        listener.sendProgressBarUpdate(this, 1, this.hasLens ? 1 : 0);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {
            if (this.sunIsVisible != this.tile.sunIsVisible) {
                listener.sendProgressBarUpdate(this, 0, this.tile.sunIsVisible ? 1 : 0);
            }
            if (this.hasLens != this.tile.hasLens) {
                listener.sendProgressBarUpdate(this, 1, this.tile.hasLens ? 1 : 0);
            }
        }

        this.sunIsVisible = this.tile.sunIsVisible;
        this.hasLens = this.tile.hasLens;
    }

    @Override
    @SideOnly(Side.CLIENT)
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
}
