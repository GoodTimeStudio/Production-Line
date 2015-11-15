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
package com.mcgoodtime.gti.common.tiles.tileslot;

import com.mcgoodtime.gti.common.recipes.IProcessable;
import com.mcgoodtime.gti.common.tiles.TileContainer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * Created by BestOwl on 2015.11.13.0013.
 *
 * @author BestOwl
 */
public class TileSlotFluidInput extends TileSlot {

    protected IProcessable processable;

    public TileSlotFluidInput(TileContainer tile, IProcessable processable) {
        this(tile, SlotMode.INPUT, processable);
    }

    public TileSlotFluidInput(TileContainer tile, SlotMode mode, IProcessable processable) {
        super(tile, mode);
        this.processable = processable;
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @Override
    public boolean canInput(ItemStack itemStack) {
        return this.processable.canProcess(itemStack);
    }

    public void drainToTank(IFluidTank tank) {
        if (this.item != null) {
            FluidStack tankFluid = tank.getFluid();

            int capacity = tank.getCapacity();
            int requiredAmount = capacity;
            Fluid requiredFluid = null;

            if (tankFluid != null) {
                requiredAmount = capacity - tank.getFluidAmount();
                requiredFluid = tankFluid.getFluid();
            }

            if (requiredAmount > 0) {
                FluidStack fluidStack  = this.drain(requiredFluid, requiredAmount);

                if (fluidStack != null) {
                    tank.fill(fluidStack, true);
                }
            }

        }
    }

    private FluidStack drain(Fluid fluid, int amount) {
        if (FluidContainerRegistry.isFilledContainer(this.item)) {
            System.out.println(1);
            FluidStack container = FluidContainerRegistry.getFluidForFilledItem(this.item);

            if (container != null && (fluid == null || fluid == container.getFluid()) && this.canInput(this.item)) {
                if (container.amount > 0 && container.amount <= amount) {
                    --this.item.stackSize;
                    if (this.item.stackSize <= 0) {
                        this.putStack(null);
                    }
                    return container;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } else {
            System.out.println(2);
        }

        return null;
    }
}
