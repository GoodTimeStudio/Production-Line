/*
 *
 * This file is part of ProductionLine, licensed under MIT License (MIT).
 *
 * Copyright (c) 2017 GoodTime Studio <https://github.com/GoodTimeStudio>
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

package com.mcgoodtime.productionline.inventory;

import com.mcgoodtime.productionline.inventory.slot.SlotInput;
import com.mcgoodtime.productionline.inventory.slot.SlotOutput;
import com.mcgoodtime.productionline.inventory.slot.SlotUpgrade;
import com.mcgoodtime.productionline.tiles.TilePackager;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlotInput;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlotPackage;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BestOwl on 2017/3/25.
 *
 * @author BestOwl
 */
public class ContainerPackager extends ContainerPL<TilePackager> {

    private int lastProgress;
    private double lastEnergy;

    public ContainerPackager(EntityPlayer player, TilePackager tile) {
        super(player, tile);
        this.addSlotToContainer(new SlotInput((TileSlotInput) tile.tileSlots.get(0), tile, 0, 56, 16));
        this.addSlotToContainer(new SlotInput((TileSlotPackage) tile.tileSlots.get(1), tile, 1, 83, 21));
        this.addSlotToContainer(new SlotDischarge(tile, 1, 2, 56, 53));
        this.addSlotToContainer(new SlotOutput(player, tile, 3, 107, 35));
        this.addSlotToContainer(new SlotUpgrade(tile, 4, 153, 26));
        this.addSlotToContainer(new SlotUpgrade(tile, 5, 153, 44));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        //listener.sendProgressBarUpdate(this, 1, lastProgress);
        //listener.sendProgressBarUpdate(this, 2, (int) lastEnergy);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {
            if (this.lastProgress != this.tile.progress) {
                //listener.sendProgressBarUpdate(this, 1, this.tile.progress);
            }
            if (this.lastEnergy != this.tile.energy) {
                //listener.sendProgressBarUpdate(this, 2, (int) this.tile.energy);
            }
        }

        this.lastEnergy = this.tile.energy;
        this.lastProgress = this.tile.progress;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int i) {
        super.updateProgressBar(id, i);
        switch (id) {
            case 1: this.tile.progress = i;
            case 2: this.tile.energy = i;
        }
    }
}
