package com.mcgoodtime.gti.common.core.coremod;

import cpw.mods.fml.relauncher.IFMLCallHook;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Map;

/**
 * Created by suhao on 2015.10.24.0024.
 *
 * @author BestOwl
 */
public class GtiSetup implements IFMLCallHook {
    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public Void call() throws Exception {
        MixinBootstrap.init();
        MixinEnvironment.getDefaultEnvironment().addConfiguration("mixin.gti-core.json");
        return null;
    }
}
