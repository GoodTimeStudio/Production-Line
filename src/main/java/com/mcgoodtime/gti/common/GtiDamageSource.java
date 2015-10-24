package com.mcgoodtime.gti.common;

import net.minecraft.util.DamageSource;

/**
 * Created by suhao on 2015.10.24.0024.
 *
 * @author BestOwl
 */
public class GtiDamageSource extends DamageSource {

    public static final GtiDamageSource salty = (GtiDamageSource) new GtiDamageSource("salty").setDamageBypassesArmor();

    public GtiDamageSource(String damageType) {
        super(damageType);
    }
}
