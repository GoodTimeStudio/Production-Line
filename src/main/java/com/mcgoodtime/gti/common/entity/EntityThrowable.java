package com.mcgoodtime.gti.common.entity;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by suhao on 2015.11.4.0004.
 *
 * @author BwstOwl
 */
public class EntityThrowable extends net.minecraft.entity.projectile.EntityThrowable {

    public EntityThrowable(World world) {
        super(world);
    }

    public EntityThrowable(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityThrowable(World world, EntityLivingBase entityLivingBase, ItemStack itemStack) {
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
        nbt.setTag("throwItem", this.getThrowItem().getTagCompound());
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
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {}
}
