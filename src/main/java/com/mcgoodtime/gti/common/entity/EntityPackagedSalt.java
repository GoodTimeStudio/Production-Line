package com.mcgoodtime.gti.common.entity;

import com.mcgoodtime.gti.common.GtiPotion;
import com.mcgoodtime.gti.common.init.GtiItems;
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

import java.util.Random;

/**
 * Created by BestOwl on 2015.11.4.0004.
 *
 * @author BestOwl
 */
public class EntityPackagedSalt extends EntityThrowable {

    public EntityPackagedSalt(World world) {
        super(world);
    }

    public EntityPackagedSalt(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }

    public EntityPackagedSalt(World world, double x, double y, double z) {
        super(world, x, y, z);
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
                ((EntityLivingBase) objectPosition.entityHit).addPotionEffect(new PotionEffect(GtiPotion.salty.id, 0, 3));
            }
        }

        Random random = new Random();
        for (int i = 0; i < 8; ++i) {
            float fmod = -0.1F - random.nextFloat() * 1.2F;
            float f1mod = -0.05F + random.nextFloat() * 0.4F;
            float f2mod = -0.1F - random.nextFloat() * 1.2F;
            this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(GtiItems.salt), this.posX + 1.0F + fmod,
                    this.posY + 1.0F + f1mod, this.posZ + 1.0F + f2mod, 0.1D, 0.1D, 0.1D);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
