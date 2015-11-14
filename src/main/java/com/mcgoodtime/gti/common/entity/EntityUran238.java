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
