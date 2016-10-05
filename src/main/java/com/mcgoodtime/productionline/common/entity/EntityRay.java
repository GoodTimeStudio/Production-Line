package com.mcgoodtime.productionline.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

    @Override
    protected ItemStack getArrowStack() {
        return null;
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
