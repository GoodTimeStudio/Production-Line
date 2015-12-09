package com.mcgoodtime.gti.common.entity;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;

/**
 * Created by suhao on 2015.10.25.0025.
 *
 */
public class GtiEntity {

    public static void registerEntities(Class<? extends Entity> entity, String name) {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entity, name, entityID);
        EntityRegistry.registerModEntity(entity, name, entityID, Gti.instance, 64, 1, true);
    }

    public static void init() {
        registerEntities(EntityThrowable.class, "ThrowableGti");
    }
}
