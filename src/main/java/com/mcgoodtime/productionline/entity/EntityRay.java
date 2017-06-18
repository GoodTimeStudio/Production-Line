package com.mcgoodtime.productionline.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by BestOwl on 2015.12.10.0010.
 *
 * @author BestOwl
 */
public class EntityRay extends EntityArrow {

    public EntityRay(World world) {
        super(world);
    }

    public EntityRay(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityRay(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
        this.pickupStatus = PickupStatus.DISALLOWED;
    }

    public EntityRay(World world, EntityLivingBase entity, double damage) {
        this(world, entity);
        setDamage(damage);
    }

    @Override
    @Nonnull
    protected ItemStack getArrowStack() {
        throw new AssertionError("This should not be called");
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.inGround) {
            this.setDead();
        }
    }
}
