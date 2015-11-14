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
package com.mcgoodtime.gti.common.tiles.tileslot;

import com.mcgoodtime.gti.common.recipes.IProcessable;
import com.mcgoodtime.gti.common.tiles.TileContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidContainerItem;

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

    public void drainToTank(FluidTank tank) {
        if (this.item != null) {
            FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(this.item);

            if (fluidStack instanceof IFluidContainerItem) {
                ((IFluidContainerItem) fluidStack).drain(this.item, FluidContainerRegistry.getContainerCapacity(this.item), true);
                System.out.println(",....");
            }
        }
    }

}
