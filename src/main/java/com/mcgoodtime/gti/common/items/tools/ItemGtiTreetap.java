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
package com.mcgoodtime.gti.common.items.tools;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.items.ItemGti;
import ic2.api.item.IBoxable;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.audio.PositionSpec;
import ic2.core.block.TileEntityBarrel;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/*
 * Created by suhao on 2015-6-4-0004.
 */
public class ItemGtiTreetap extends ItemGti implements IBoxable {

    public ItemGtiTreetap(String name, int damage) {
        super(name);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        Block block = world.getBlock(x, y, z);
        if(StackUtil.equals(block, Ic2Items.blockBarrel)) {
            return ((TileEntityBarrel)world.getTileEntity(x, y, z)).useTreetapOn(entityplayer, side);
        } else if(Ic2Items.rubberWood != null && StackUtil.equals(block, Ic2Items.rubberWood)) {
            attemptExtract(entityplayer, world, x, y, z, side, null);
            if(IC2.platform.isSimulating()) {
                itemstack.damageItem(1, entityplayer);
            }

            return true;
        } else {
            return false;
        }
    }

    public static void ejectHarz(World world, int x, int y, int z, int side, int quantity) {
        double ejectX = (double)x + 0.5D;
        double ejectY = (double)y + 0.5D;
        double ejectZ = (double)z + 0.5D;
        if(side == 2) {
            ejectZ -= 0.3D;
        } else if(side == 5) {
            ejectX += 0.3D;
        } else if(side == 3) {
            ejectZ += 0.3D;
        } else if(side == 4) {
            ejectX -= 0.3D;
        }

        for(int i = 0; i < quantity; ++i) {
            EntityItem entityitem = new EntityItem(world, ejectX, ejectY, ejectZ, Ic2Items.resin.copy());
            entityitem.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(entityitem);
        }

    }

    public static boolean attemptExtract(EntityPlayer entityplayer, World world, int x, int y, int z, int side, List<ItemStack> stacks) {
        int meta = world.getBlockMetadata(x, y, z);
        if(meta >= 2 && meta % 6 == side) {
            if(meta < 6) {
                if(IC2.platform.isSimulating()) {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 6, 3);
                    if(stacks != null) {
                        stacks.add(StackUtil.copyWithSize(Ic2Items.resin, world.rand.nextInt(3) + 1));
                    } else {
                        ejectHarz(world, x, y, z, side, world.rand.nextInt(3) + 1);
                    }

                    if(entityplayer != null) {
                        IC2.achievements.issueAchievement(entityplayer, "acquireResin");
                    }

                    Block woodBlock = StackUtil.getBlock(Ic2Items.rubberWood);
                    world.scheduleBlockUpdate(x, y, z, woodBlock, woodBlock.tickRate(world));
                }

                if(IC2.platform.isRendering() && entityplayer != null) {
                    IC2.audioManager.playOnce(entityplayer, PositionSpec.Hand, "Tools/Treetap.ogg", true, IC2.audioManager.getDefaultVolume());
                }

                return true;
            } else {
                if(IC2.platform.isSimulating() && world.rand.nextInt(5) == 0) {
                    world.setBlockMetadataWithNotify(x, y, z, 1, 3);
                }

                if(world.rand.nextInt(5) == 0) {
                    if(IC2.platform.isSimulating()) {
                        ejectHarz(world, x, y, z, side, 1);
                        if(stacks != null) {
                            stacks.add(StackUtil.copyWithSize(Ic2Items.resin, 1));
                        } else {
                            ejectHarz(world, x, y, z, side, 1);
                        }
                    }

                    if(IC2.platform.isRendering() && entityplayer != null) {
                        IC2.audioManager.playOnce(entityplayer, PositionSpec.Hand, "Tools/Treetap.ogg", true, IC2.audioManager.getDefaultVolume());
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeStoredInToolbox(ItemStack itemstack) {
        return true;
    }

    @Override
    public String getTextureFolder() {
        return"tools/";
    }

}
