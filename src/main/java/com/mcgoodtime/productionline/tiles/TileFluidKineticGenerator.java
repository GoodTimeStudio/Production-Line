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

import com.mcgoodtime.productionline.recipes.FluidKineticGeneratorRecipes;
import com.mcgoodtime.productionline.recipes.RecipePart;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlotFluidInput;
import com.mcgoodtime.productionline.tiles.tileslots.TileSlotOutput;
import ic2.api.energy.tile.IKineticSource;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.Collections;
import java.util.List;

/**
 * The BlockFluidKineticGenerator tile.
 *
 * @author liach
 */
public class TileFluidKineticGenerator extends TileContainer implements IKineticSource, IWrenchable {

    private int timer;
    public final int kuOutput = 32;
    public FluidTank fluidTank = new FluidTank(10000);

    public TileFluidKineticGenerator() {
        this.tileSlots.add(new TileSlotFluidInput(this, FluidKineticGeneratorRecipes.instance, this.fluidTank));
        this.tileSlots.add(new TileSlotOutput(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.fluidTank.readFromNBT(nbt.getCompoundTag("fluidTank"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagCompound fluidTag = new NBTTagCompound();
        this.fluidTank.writeToNBT(fluidTag);
        nbt.setTag("fluidTank", fluidTag);
        return nbt;
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {
            boolean needUpdate = false;

            if (this.fluidTank.getFluidAmount() <= this.fluidTank.getCapacity()) {
                for (TileSlot tileSlot : this.tileSlots) {
                    if (tileSlot instanceof TileSlotFluidInput) {
                        ((TileSlotFluidInput) tileSlot).drainToTank();
                        needUpdate = true;
                    }
                }
            }


            if (this.fluidTank.getFluid() != null && this.maxrequestkineticenergyTick(this.facing) > 0) {
                int amount = 0;
                for (RecipePart recipePart : FluidKineticGeneratorRecipes.instance.getProcessRecipesList()) {
                    FluidStack fluidStack = ((FluidKineticGeneratorRecipes.RecipePartFluidKineticGenerator) recipePart).fluidStack;
                    if (fluidStack.isFluidEqual(this.fluidTank.getFluid())) {
                        amount = fluidStack.amount;

                    }
                }

                if (this.fluidTank.getFluidAmount() >= amount) {
                    this.setActive(true);

                    if (this.timer == 20) {
                        this.timer = 0;
                        this.fluidTank.getFluid().amount -= amount;
                    }
                    this.timer++;

                } else {
                    this.setActive(false);
                    this.timer = 0;
                }

                if (this.fluidTank.getFluidAmount() < 0) {
                    this.fluidTank.getFluid().amount = 0;
                }

            }
            else {
                this.setActive(false);
            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    @Override
    public String getName() {
        return "FluidKineticGenerator";
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) fluidTank;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public int maxrequestkineticenergyTick(EnumFacing from) {
        return from != this.facing ? 0 : this.kuOutput;
    }

    @Override
    public int requestkineticenergy(EnumFacing from, int i) {
        return from != this.facing ? 0 : (this.fluidTank.getFluidAmount() > 0 ? Math.min(this.kuOutput, i) : 0);
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos pos) {
        return facing;
    }

    @Override
    public boolean setFacing(World world, BlockPos pos, EnumFacing facing, EntityPlayer player) {
        if (facing != this.facing) {
            this.facing = facing;
            return true;
        }
        return false;
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return Collections.singletonList(new ItemStack(iBlockState.getBlock(), 1, iBlockState.getBlock().getMetaFromState(iBlockState)));
    }

}
