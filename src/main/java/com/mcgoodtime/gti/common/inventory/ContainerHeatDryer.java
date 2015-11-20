/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.inventory.slot.SlotInput;
import com.mcgoodtime.gti.common.inventory.slot.SlotOutput;
import com.mcgoodtime.gti.common.inventory.slot.SlotUpgrade;
import com.mcgoodtime.gti.common.tiles.TileHeatDryer;
import com.mcgoodtime.gti.common.tiles.tileslot.TileSlotInput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;

/*
 * Created by suhao on 2015.7.10.
 */
public class ContainerHeatDryer extends ContainerGti<TileHeatDryer>{

    private int lastProgress;
    private int lastEnergy;

    public ContainerHeatDryer(EntityPlayer player, TileHeatDryer tile) {
        super(player, tile);

        this.addSlotToContainer(new SlotInput((TileSlotInput) tile.tileSlots.get(0), tile, 0, 56, 16));
        this.addSlotToContainer(new SlotDischarge(tile, 1, 1, 56, 53));
        this.addSlotToContainer(new SlotOutput(player, tile, 2, 107, 35));
        this.addSlotToContainer(new SlotUpgrade(tile, 3, 153, 26));
        this.addSlotToContainer(new SlotUpgrade(tile, 4, 153, 44));
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 1, lastProgress);
        iCrafting.sendProgressBarUpdate(this, 2, lastEnergy);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object object : this.crafters) {
            ICrafting icrafting = (ICrafting) object;

            if (this.lastProgress != this.tile.progress) {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.progress);
            }
            if (this.lastEnergy != this.tile.energy) {
                icrafting.sendProgressBarUpdate(this, 2, (int) this.tile.energy);
            }
        }
    }

    @Override
    public void updateProgressBar(int id, int i) {
        super.updateProgressBar(id, i);
        switch (id) {
            case 1: this.tile.progress = i;
            case 2: this.tile.energy = i;
        }
    }
}
