package com.mcgoodtime.gti.common.entity;

import com.mcgoodtime.gti.common.GtiPotion;
import com.mcgoodtime.gti.common.GtiUtil;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by BestOwl on 2015.11.2.0002.
 *
 * @author BestOwl
 */
public class EntityPackedSalt extends EntityThrowable {

    public EntityPackedSalt(World world) {
        super(world);
    }

    public EntityPackedSalt(World world, EntityLivingBase livingBase) {
        super(world, livingBase);
    }

    public EntityPackedSalt(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition objectPosition) {
        GtiUtil.onThrowItemImpact(objectPosition, this, GtiItems.packagedSalt, GtiPotion.salty, 0, 3);
    }
}
