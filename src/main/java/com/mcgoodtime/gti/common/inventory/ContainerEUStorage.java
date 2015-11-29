package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.eustorage.TileEUStorage;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class ContainerEUStorage<T extends TileEUStorage> extends ContainerGti<T> {

    public double lastEnergy;
    public int lastMode;

    public ContainerEUStorage(EntityPlayer player, T tile) {
        super(player, tile, 196);
        this.addSlotToContainer(new SlotDischarge(this.tile, this.tile.tier, 0, 56, 53));
        this.addSlotToContainer(new Slot(this.tile, 1, 56, 17));
        for(int i = 0; i < 4; i++) {
            this.addSlotToContainer(new SlotArmor(player.inventory, i, 8 + i * 18, 84));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, (int) this.tile.energy);
        iCrafting.sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object object : this.crafters) {
            if (this.lastEnergy != this.tile.energy) {
                ((ICrafting) object).sendProgressBarUpdate(this, 0, (int) this.tile.energy);
            }
            if (this.lastMode != this.tile.redstoneMode.ordinal()) {
                ((ICrafting) object).sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
            }
        }

        this.lastEnergy = this.tile.energy;
        this.lastMode = this.tile.redstoneMode.ordinal();
    }

    @Override
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
