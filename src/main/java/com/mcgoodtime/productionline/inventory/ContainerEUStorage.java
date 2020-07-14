package com.mcgoodtime.productionline.inventory;

import com.mcgoodtime.productionline.tiles.eustorage.TileEUStorage;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class ContainerEUStorage<T extends TileEUStorage> extends ContainerPL<T> {

    public double lastEnergy;
    public int lastMode;

    public ContainerEUStorage(EntityPlayer player, T tile) {
        super(player, tile, 196);
        this.addSlotToContainer(new SlotDischarge(this.tile, this.tile.tier, 0, 56, 53));
        this.addSlotToContainer(new Slot(this.tile, 1, 56, 17));
        for (int i = 0; i < 4; i++) {
            this.addSlotToContainer(new SlotArmor(player.inventory, EntityEquipmentSlot.values()[i + 2], 8 + i * 18, 84));
        }
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

        for (IContainerListener listener : this.listeners) {
            if (this.lastEnergy != this.tile.energy) {
                //listener.sendProgressBarUpdate(this, 0, (int) this.tile.energy);
            }
            if (this.lastMode != this.tile.redstoneMode.ordinal()) {
                //listener.sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
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
