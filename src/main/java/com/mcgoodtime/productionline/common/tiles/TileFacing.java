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
package com.mcgoodtime.productionline.common.tiles;

import com.mcgoodtime.productionline.common.blocks.BlockMachine;
import com.mcgoodtime.productionline.common.blocks.BlockPL;
import com.mcgoodtime.productionline.common.network.PLNetwork;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by suhao on 2015.7.1.
 *
 * @author suhao
 */
public class TileFacing extends TileEntity {

    public EnumFacing facing;
    public boolean active;

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.facing = EnumFacing.getFront(nbt.getShort("facing"));
        this.active = nbt.getBoolean("active");

    }

    /**
     * Called from Chunk.setBlockIDWithMetadata and Chunk.fillChunk, determines if this tile entity should be re-created when the ID, or Metadata changes.
     * Use with caution as this will leave straggler TileEntities, or create conflicts with other TileEntities if not used properly.
     *
     * @param world    Current world
     * @param pos      Tile's world position
     * @param oldState The old ID of the block
     * @return true forcing the invalidation of the existing TE, false not to invalidate the existing TE
     */
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);
        if (facing != null) {
            nbt.setShort("facing", ((short) facing.getIndex()));
        }
        nbt.setBoolean("active", active);
        return nbt;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.getUpdateTag();
        if (facing != null) {
            compound.setShort("facing", ((short) facing.getIndex()));
        }
        compound.setBoolean("active", active);
        return compound;
    }

    public void setActive(boolean active) {
        this.active = active;
        PLNetwork.updateBlockDisplayState(this);
        this.getWorld().setBlockState(this.pos, this.getWorld().getBlockState(this.pos).withProperty(BlockMachine.PROPERTY_ACTIVE, active));
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
        PLNetwork.updateBlockDisplayState(this);
        this.getWorld().setBlockState(this.pos, this.getWorld().getBlockState(this.pos).withProperty(BlockPL.PROPERTY_FACING, facing));
    }
}
