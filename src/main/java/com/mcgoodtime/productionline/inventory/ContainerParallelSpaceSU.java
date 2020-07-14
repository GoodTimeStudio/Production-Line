package com.mcgoodtime.productionline.inventory;

import com.mcgoodtime.productionline.tiles.eustorage.TileEUStorage;
import com.mcgoodtime.productionline.tiles.eustorage.TileParallelSpaceSU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class ContainerParallelSpaceSU extends ContainerPL<TileParallelSpaceSU> {

    public double lastEnergy;
    public int lastMode;

    public ContainerParallelSpaceSU(EntityPlayer player, TileParallelSpaceSU tile) {
        super(player, tile);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        //listener.sendProgressBarUpdate(this, 0, (int) this.tile.energy);
        //listener.sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener object : this.listeners) {
            if (this.lastEnergy != this.tile.energy) {
                //object.sendProgressBarUpdate(this, 0, (int) this.tile.energy);
            }
            if (this.lastMode != this.tile.redstoneMode.ordinal()) {
                //object.sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
            }
        }

        this.lastEnergy = this.tile.energy;
        this.lastMode = this.tile.redstoneMode.ordinal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int var) {
        super.updateProgressBar(id, var);
        switch (id) {
            case 0:
                this.tile.energy = var;
                break;
            case 1:
                this.tile.redstoneMode = TileEUStorage.RedstoneMode.values()[var];
                break;
        }
    }
}
