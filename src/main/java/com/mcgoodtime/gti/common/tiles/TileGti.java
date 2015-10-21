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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by suhao on 2015.7.1.
 *
 * @author suhao
 */
public class TileGti extends TileEntity {

    public short facing;
    public boolean isBurn;

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        facing = nbt.getShort("facing");
        isBurn = nbt.getBoolean("isBurn");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("facing", facing);
        nbt.setBoolean("isBurn", isBurn);
    }

    public short getFacing() {
        return facing;
    }

    public boolean isBurn() {
        return isBurn;
    }

    public void setFacing(short facing) {
        this.facing = facing;
    }

    public void setIsBurn(boolean isBurn) {
        this.isBurn = isBurn;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound nbt = pkt.func_148857_g();
        this.facing = nbt.getShort("facing");
        this.isBurn = nbt.getBoolean("isBurn");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound sync = new NBTTagCompound();
        sync.setShort("facing", this.facing);
        sync.setBoolean("isBurn", this.isBurn);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, sync);
    }
}
