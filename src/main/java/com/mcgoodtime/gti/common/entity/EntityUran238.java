package com.mcgoodtime.gti.common.entity;

import ic2.core.IC2Potion;
import ic2.core.Ic2Items;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by suhao on 2015.10.24.0024.
 *
 * @author BestOwl
 */
public class EntityUran238 extends EntityThrowable {

    public EntityUran238(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }

    public EntityUran238(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityUran238(World world) {
        super(world);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition objectPosition) {
        if (objectPosition.entityHit != null) {
            byte b0 = 0;

            if (objectPosition.entityHit instanceof EntityBlaze) {
                b0 = 3;
            }

            objectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
            if (objectPosition.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase) objectPosition.entityHit).addPotionEffect(new PotionEffect(IC2Potion.radiation.id, 200, 0));
            }
        }

        for (int i = 0; i < 8; ++i) {
            this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(Ic2Items.Uran238.getItem()), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
