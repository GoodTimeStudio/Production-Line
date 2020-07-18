/*
 * This file is part of Production Line, licensed under MIT License (MIT).
 *
 * Copyright (c) 2020 GoodTime Studio <https://github.com/GoodTimeStudio>
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

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * Created by NightOwl on 2020/7/17
 *
 * @author NightOwl
 */
public class TileWaterGenerator extends TileEntity implements ITickable {

    private int energyOutput;
    private int timer;

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {
        if (!this.world.isRemote) {
            // for better performance, wait 300 ticks before checking water environment
            if (timer == 0) {
                energyOutput = 0;

                BlockPos pos = this.getPos();

                if(isNotWater(pos.east()) && isNotWater(pos.south()) && isNotWater(pos.west()) && isNotWater(pos.north()))
                {
                    BlockPos down = this.getPos().down();
                    isWater(down);
                    isWater(down.east());
                    isWater(down.south());
                    isWater(down.west());
                    isWater(down.north());
                    isWater(down.north().east());
                    isWater(down.north().west());
                    isWater(down.south().east());
                    isWater(down.south().west());
                }

            } else if (timer == 300) {
                timer = -1;
            }
            timer += 1;

            TileEntity te = this.world.getTileEntity(this.pos.up());
            if (te == null) {
                return;
            }
            if (te.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.DOWN)&&energyOutput>0) {
                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.DOWN);
                storage.receiveEnergy(energyOutput, false);
            }
        }
    }

    private void isWater(BlockPos pos) {
        if (this.world.getBlockState(pos).getBlock() == Blocks.WATER) {
            energyOutput += 1;
        }
    }

    private boolean isNotWater(BlockPos pos){
        if (this.world.getBlockState(pos).getBlock() == Blocks.WATER) {
            energyOutput = 0;
            return true;
        }else{
            return false;
        }
    }

}
