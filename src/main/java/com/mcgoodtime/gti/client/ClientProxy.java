package com.mcgoodtime.gti.client;

import com.mcgoodtime.gti.common.core.CommonProxy;
import com.mcgoodtime.gti.common.entity.EntityThrowableGti;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * Created by suhao on 2015.10.25.0025.
 *
 * @author BestOwl
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowableGti.class, new RenderEntityThrowable());
    }
}
