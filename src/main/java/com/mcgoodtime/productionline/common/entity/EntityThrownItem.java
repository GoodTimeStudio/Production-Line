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
package com.mcgoodtime.productionline.common.entity;

import com.mcgoodtime.productionline.common.event.EntityThrowableImpactEvent;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by suhao on 2015.11.4.0004.
 *
 * @author BwstOwl
 */
public class EntityThrownItem extends EntityThrowable {

    public EntityThrownItem(World world) {
        super(world);
    }

    public EntityThrownItem(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityThrownItem(World world, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        super(world, entityLivingBase);
        this.setThrowItem(itemStack);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObjectByDataType(23, 5);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setTag("throwItem", this.getThrowItem().writeToNBT(nbt.getCompoundTag("throwItem")));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setThrowItem(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("throwItem")));
    }

    public ItemStack getThrowItem() {
        return this.getDataWatcher().getWatchableObjectItemStack(23);
    }

    public void setThrowItem(ItemStack itemStack) {
        DataWatcher watcher = this.getDataWatcher();
        watcher.updateObject(23, itemStack.copy());
        watcher.setObjectWatched(23);
    }

    /**
     * Called when this EntityThrownItem hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        MinecraftForge.EVENT_BUS.post(new EntityThrowableImpactEvent(this, movingObjectPosition));
    }
}
