package com.mcgoodtime.gti.common.entity;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.init.GtiItems;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import ic2.core.Ic2Items;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

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
        registerEntities(EntityThrowableGti.class, "ThrowableGti");
    }
}
