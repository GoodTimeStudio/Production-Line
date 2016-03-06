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

package com.mcgoodtime.productionline.common.inventory;

import com.mcgoodtime.productionline.common.inventory.slot.SlotOutput;
import com.mcgoodtime.productionline.common.tiles.TileFluidKineticGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ContainerFluidKineticGenerator extends ContainerPL<TileFluidKineticGenerator> {

    public int fluidAmount;
    public int fluidID;

    public ContainerFluidKineticGenerator(EntityPlayer player, TileFluidKineticGenerator tile) {
        super(player, tile);

        this.addSlotToContainer(new Slot(tile, 0, 27, 21));
        this.addSlotToContainer(new SlotOutput(player, tile, 1, 27, 54));
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tile.fluidTank.getFluidAmount());
        if (this.tile.fluidTank.getFluid() != null) {
            iCrafting.sendProgressBarUpdate(this, 1, this.tile.fluidTank.getFluid().getFluidID());
        }
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object object : this.crafters) {
            if (this.fluidAmount != this.tile.fluidTank.getFluidAmount()) {
                ((ICrafting) object).sendProgressBarUpdate(this, 0, this.tile.fluidTank.getFluidAmount());
            }
            if (this.tile.fluidTank.getFluid() != null && this.fluidID != this.tile.fluidTank.getFluid().getFluidID()) {
                ((ICrafting) object).sendProgressBarUpdate(this, 1, this.tile.fluidTank.getFluid().getFluidID());
            }
        }

        this.fluidAmount = this.tile.fluidTank.getFluidAmount();
        if (this.tile.fluidTank.getFluid() != null) {
            this.fluidID = this.tile.fluidTank.getFluid().getFluidID();
        }
    }

    @Override
    public void updateProgressBar(int id, int var) {
        super.updateProgressBar(id, var);
        switch (id) {
            case 0: if (this.tile.fluidTank.getFluid() != null) {
                        this.tile.fluidTank.getFluid().amount = var;
                    }
            case 1: if (FluidRegistry.getFluid(var) != null) {
                        this.tile.fluidTank.setFluid(new FluidStack(FluidRegistry.getFluid(var), this.tile.fluidTank.getFluidAmount()));
                    }
        }
    }
}
