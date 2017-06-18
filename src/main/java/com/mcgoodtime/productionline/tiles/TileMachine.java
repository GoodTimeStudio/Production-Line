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

package com.mcgoodtime.productionline.tiles;

import ic2.api.tile.IWrenchable;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BestOwl on 2017/3/29.
 *
 * base machine tile.
 * @author BestOwl
 */
public abstract class TileMachine extends TileElectricContainer implements IWrenchable, IUpgradableBlock {

    public TileMachine(int energyTick, int maxEnergy, int sinkTier) {
        super(energyTick, maxEnergy, sinkTier);
    }

    /**
     * Get direction the block is facing.
     * <p>
     * The direction typically refers to the front/main/functionally dominant side of a block.
     *
     * @param world World containing the block.
     * @param pos   The block's current position in the world.
     * @return Current block facing.
     */
    @Override
    public EnumFacing getFacing(World world, BlockPos pos) {
        return this.facing;
    }

    /**
     * Set the block's facing to face towards the specified direction.
     * <p>
     * Contrary to Block.rotateBlock the block should always face the requested direction after
     * successfully processing this method.
     *
     * @param world        World containing the block.
     * @param pos          The block's current position in the world.
     * @param newDirection Requested facing, see {@link #getFacing}.
     * @param player       Player causing the action, may be null.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
        this.setFacing(newDirection);
        return true;
    }

    /**
     * Determine if the wrench can be used to remove the block.
     *
     * @param world  World containing the block.
     * @param pos    The block's current position in the world.
     * @param player Player causing the action, may be null.
     * @return true if allowed, false otherwise.
     */
    @Override
    public boolean wrenchCanRemove(World world, BlockPos pos, EntityPlayer player) {
        return true;
    }

    /**
     * Determine the items the block will drop when the wrenching is successful.
     * <p>
     * The ItemStack will be copied before creating the EntityItem.
     *
     * @param world   World containing the block.
     * @param pos     The block's current position in the world.
     * @param state   The block's block state before removal.
     * @param te      The block's tile entity before removal, if any, may be null.
     * @param player  Player removing the block, may be null.
     * @param fortune Fortune level for drop calculation.
     * @return ItemStacks to drop, may be empty.
     */
    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
        return Collections.singletonList(new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean useEnergy(double amount) {
        if(this.energy >= amount) {
            this.energy -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing, UpgradableProperty.Augmentable);
    }
}
