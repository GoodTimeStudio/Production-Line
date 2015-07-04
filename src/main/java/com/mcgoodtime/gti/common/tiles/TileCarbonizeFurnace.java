/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.tiles;

import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotConsumableFuel;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotProcessableSmelting;

/*
 * Created by suhao on 2015.7.3.
 */
public class TileCarbonizeFurnace extends TileEntityInventory {

    public int fuel = 0;
    public int maxFuel = 0;
    public short progress = 0;
    public final short operationLength = 160;
    public final InvSlotProcessable inputSlot = new InvSlotProcessableSmelting(this, "input", 0, 1);
    public final InvSlotOutput outputSlot = new InvSlotOutput(this, "output", 2, 1);
    public final InvSlotConsumableFuel fuelSlot = new InvSlotConsumableFuel(this, "fuel", 1, 1, true);

    @Override
    public String getInventoryName() {
        return "Carbonize Furnace";
    }

    @Override
    public void setFacing(short facing) {
        super.setFacing(facing);
    }

    public int gaugeProgressScaled(int i) {
        return this.progress * i / 160;
    }

    public int gaugeFuelScaled(int i) {
        if(this.maxFuel == 0) {
            this.maxFuel = this.fuel;
            if(this.maxFuel == 0) {
                this.maxFuel = 160;
            }
        }

        return this.fuel * i / this.maxFuel;
    }
}
