/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.gti.common.network;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.tiles.eustorage.TileEUStorage;
import com.mcgoodtime.gti.common.tiles.TileGti;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by BestOwl on 2015.10.30.0030.
 *
 * @author BestOwl
 */
public class GtiNetwork {
    public final static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Gti.MOD_ID);;

    static {
        network.registerMessage(MessageBlockDisplayState.Handler.class, MessageBlockDisplayState.class, 0, Side.CLIENT);
        network.registerMessage(MessageEUStorage.Handler.class, MessageEUStorage.class, 2, Side.SERVER);
    }

    public static void updateBlockDisplayState(TileGti tile) {
        network.sendToAllAround(new MessageBlockDisplayState(tile.xCoord, tile.yCoord, tile.zCoord, tile.active, tile.facing),
                new NetworkRegistry.TargetPoint(tile.getWorldObj().getWorldInfo().getVanillaDimension(),
                tile.xCoord, tile.yCoord, tile.zCoord, 64));
    }

    public static void updateTileEUStorage(TileEUStorage tile) {
        network.sendToServer(new MessageEUStorage(tile));
    }

    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }
}
