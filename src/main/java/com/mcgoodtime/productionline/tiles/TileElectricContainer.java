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

package com.mcgoodtime.productionline.tiles;

import com.mcgoodtime.productionline.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlotDischarge;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by BestOwl on 2015.10.26.0026.
 *
 * @author BestOwl
 */
public abstract class TileElectricContainer extends TileContainer implements IEnergySink {

    public final int energyTick;
    /** The number of remaining battery */
    public double energy;
    /** The number of that can storage battery */
    public final int maxEnergy;
    /** Determine the tier of this energy sink */
    public int tier;

    public TileElectricContainer (int energyTick, int maxEnergy, int sinkTier) {
        this.energyTick = energyTick;
        this.maxEnergy = maxEnergy;
        this.tier = sinkTier;
    }


    @Override
    public void update() {
        if (!this.world.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));

            if((double)this.maxEnergy - this.energy >= 1.0D) {
                for (TileSlot tileSlot : this.tileSlots) {
                    if (tileSlot instanceof TileSlotDischarge) {
                        double amount = ((TileSlotDischarge) tileSlot).discharge((double) this.maxEnergy - this.energy, false);
                        if(amount > 0.0D) {
                            this.energy += amount;
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy = nbt.getDouble("energy");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        nbt.setDouble("energy", this.energy);
        return nbt;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return true;
    }

    /**
     * Determine how much energy the sink accepts.
     * Make sure that injectEnergy() does accepts energy if demandsEnergy() returns anything > 0.
     *
     * @note Modifying the energy net from this method is disallowed.
     * @return max accepted input in eu
     */
    @Override
    public double getDemandedEnergy() {
        return (double) this.maxEnergy - this.energy;
    }

    /**
     * Determine the tier of this energy sink.
     * 1 = LV, 2 = MV, 3 = HV, 4 = EV etc.
     *
     * @note Modifying the energy net from this method is disallowed.
     * @note Return Integer.MAX_VALUE to allow any voltage.
     * @return tier of this energy sink
     */
    @Override
    public int getSinkTier() {
        return this.tier;
    }

    @Override
    public double injectEnergy(EnumFacing enumFacing, double amount, double voltage) {
        if(this.energy >= (double) this.maxEnergy) {
            return amount;
        } else {
            this.energy += amount;
            return 0.0D;
        }
    }

    /**
     * Called when the chunk this TileEntity is on is Unloaded.
     */
    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        if (!this.world.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
    }

    /**
     * invalidates a tile entity
     */
    @Override
    public void invalidate() {
        super.invalidate();
        if (!this.world.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
    }
}
