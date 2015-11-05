/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileGti;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by suhao on 2015.6.23.
 *
 * @author suhao
 */
public class BlockCarbonizeFurnace extends BlockContainerGti {

    public BlockCarbonizeFurnace() {
        super(Material.iron, "CarbonizeFurnace");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote) {
            entityPlayer.openGui(Gti.instance, GuiHandler.EnumGui.CarbonizeFurnace.ordinal(), world, x, y, z);
        } else {
            entityPlayer.isInvisibleToPlayer(entityPlayer);
        }
        return true;
    }

    @Override
    protected Class<? extends TileGti> getTileEntityClass() {
        return TileCarbonizeFurnace.class;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if ( ((TileGti)world.getTileEntity(x, y, z)).active) {
            float f2;
            float fmod;
            float f1mod;
            float f2mod;

            float f = (float)x + 1.0F;
            float f1 = (float)y + 1.0F;
            f2 = (float)z + 1.0F;

            for(int i = 0; i < 4; ++i) {
                fmod = -0.2F - random.nextFloat() * 0.6F;
                f1mod = -0.1F + random.nextFloat() * 0.2F;
                f2mod = -0.2F - random.nextFloat() * 0.6F;
                world.spawnParticle("smoke", (double)(f + fmod), (double)(f1 + f1mod), (double)(f2 + f2mod), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
