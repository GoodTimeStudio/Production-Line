package com.mcgoodtime.gti.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * Created by suhao on 2015.11.5.0005.
 *
 * @author BestOwl
 */
public class GtiEffect extends PotionEffect {

    protected float hp = 1F;
    protected int timer;
    protected int level;

    public GtiEffect(int id, int durationTime, int level) {
        super(id, durationTime, level);
        if (id == GtiPotion.salty.id) {
            this.timer = 20;
        }
        this.level = level;
    }

    @Override
    public boolean onUpdate(EntityLivingBase entityLivingBase) {
        if (this.getPotionID() == GtiPotion.salty.id) {
            if (this.level > 0) {
                if (((GtiPotion) Potion.potionTypes[this.getPotionID()]).isReady(this)) {
                    ((GtiPotion) Potion.potionTypes[this.getPotionID()]).performEffect(entityLivingBase, hp);
                }
                return true;
            }
        }

        return false;
    }
}
