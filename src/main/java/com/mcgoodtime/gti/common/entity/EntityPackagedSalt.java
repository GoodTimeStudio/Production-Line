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
package com.mcgoodtime.gti.common.entity;

import com.mcgoodtime.gti.common.GtiPotion;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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
            objectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 3F);
            if (objectPosition.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase) objectPosition.entityHit).addPotionEffect(new PotionEffect(GtiPotion.salty.id, 0, 3));
            }
        }

        for (int i = 0; i < 8; ++i) {
            float fmod = (float) (1.2 - (Math.random() * 2.4));
            float f1mod = (float) (0.5 - (Math.random() * 1.0));
            float f2mod = (float) (1.2 - (Math.random() * 2.4));

            this.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(GtiItems.salt), this.posX + fmod,
                    this.posY + f1mod, this.posZ + f2mod, 0.1D, 0.1D, 0.1D);

            if (!this.worldObj.isRemote) {
                EntityItem entityItem = new EntityItem(this.worldObj, this.posX + fmod, this.posY + f1mod, this.posZ + f2mod, new ItemStack(GtiItems.salt));
                this.worldObj.spawnEntityInWorld(entityItem);
            }
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
