package com.mcgoodtime.gti.common.network;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by BestOwl on 2015.10.30.0030.
 *
 * @author BestOwl
 */
public class GtiNetwork {

    private static SimpleNetworkWrapper network;

    public static void init() {
        if (network == null) {
            network = NetworkRegistry.INSTANCE.newSimpleChannel(Gti.MOD_ID);
            network.registerMessage(MessageBlockState.Handler.class, MessageBlockState.class, 0, Side.CLIENT);
        }
    }

    public static void updateBlockState(int x, int y, int z, boolean active, short facing) {
        network.sendToAll(new MessageBlockState(x, y, z, active, facing));
    }
}
