package com.mcgoodtime.gti.client;

import com.mcgoodtime.gti.common.core.Gti;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by BestOwl on 2015.12.10.0010.
 *
 * @author BestOwl
 */
public class RenderEntityRay extends RenderArrow {
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/entity/ray.png");
    }
}
