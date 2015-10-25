package com.mcgoodtime.gti.client;

import com.mcgoodtime.gti.common.core.CommonProxy;
import com.mcgoodtime.gti.common.entity.EntityUran238;
import cpw.mods.fml.client.registry.RenderingRegistry;
import ic2.core.Ic2Items;
import net.minecraft.client.renderer.entity.RenderSnowball;

/**
 * Created by suhao on 2015.10.25.0025.
 *
 * @author BestOwl
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityUran238.class, new RenderSnowball(Ic2Items.Uran238.getItem()));
    }
}
