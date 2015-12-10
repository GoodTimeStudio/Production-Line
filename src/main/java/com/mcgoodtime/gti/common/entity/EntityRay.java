package com.mcgoodtime.gti.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Created by BestOwl on 2015.12.10.0010.
 *
 * @author BestOwl
 */
public class EntityRay extends EntityArrow {

    public float damage;

    public EntityRay(World world) {
        super(world);
    }

    public EntityRay(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityRay(World world, EntityLivingBase entityLivingBase, float damage) {
        super(world, entityLivingBase, damage);
        this.damage = damage;
    }

    public EntityRay(World world, EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase1, float f, float f1) {
        super(world, entityLivingBase, entityLivingBase1, f, f1);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        if (movingobjectposition != null) {
            if (movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityLivingBase) {
                movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeArrowDamage(this, this.shootingEntity), this.damage);
            }
        }
    }
}
