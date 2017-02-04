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
package com.mcgoodtime.productionline.common.items.tools;

import com.mcgoodtime.productionline.client.IItemModelProvider;
import com.mcgoodtime.productionline.common.items.ItemPL;
import ic2.api.item.IBoxable;
import ic2.core.IC2;
import ic2.core.audio.PositionSpec;
import ic2.core.block.BlockRubWood;
import ic2.core.item.type.MiscResourceType;
import ic2.core.ref.BlockName;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/*
 * Created by suhao on 2015-6-4-0004.
 */
public class ItemPLTreetap extends ItemPL implements IBoxable, IItemModelProvider {

    public ItemPLTreetap(String name, int damage) {
        super(name);
        this.setMaxDamage(damage);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOffset, float yOffset, float zOffset) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block == BlockName.rubber_wood.getInstance()) {
            attemptExtract(player, world, pos, side, state, null);
            if (!world.isRemote) {
                stack.damageItem(1, player);
            }

            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.PASS;
        }
    }

    public static boolean attemptExtract(EntityPlayer player, World world, BlockPos pos, EnumFacing side, IBlockState state, List<ItemStack> stacks) {
        BlockRubWood.RubberWoodState rwState = state.getValue(BlockRubWood.stateProperty);
        if (!rwState.isPlain() && rwState.facing == side) {
            if (rwState.wet) {
                if (!world.isRemote) {
                    world.setBlockState(pos, state.withProperty(BlockRubWood.stateProperty, rwState.getDry()));
                    if (stacks != null) {
                        stacks.add(StackUtil.copyWithSize(ItemName.misc_resource.getItemStack(MiscResourceType.resin), world.rand.nextInt(3) + 1));
                    } else {
                        ejectResin(world, pos, side, world.rand.nextInt(3) + 1);
                    }

                    if (player != null) {
                        IC2.achievements.issueAchievement(player, "acquireResin");
                    }
                }

                if (world.isRemote && player != null) {
                    IC2.audioManager.playOnce(player, PositionSpec.Hand, "Tools/Treetap.ogg", true, IC2.audioManager.getDefaultVolume());
                }

                return true;
            } else {
                if (!world.isRemote && world.rand.nextInt(5) == 0) {
                    world.setBlockState(pos, state.withProperty(BlockRubWood.stateProperty, BlockRubWood.RubberWoodState.plain_y));
                }

                if (world.rand.nextInt(5) == 0) {
                    if (!world.isRemote) {
                        ejectResin(world, pos, side, 1);
                        if (stacks != null) {
                            stacks.add(ItemName.misc_resource.getItemStack(MiscResourceType.resin));
                        } else {
                            ejectResin(world, pos, side, 1);
                        }
                    }

                    if (world.isRemote && player != null) {
                        IC2.audioManager.playOnce(player, PositionSpec.Hand, "Tools/Treetap.ogg", true, IC2.audioManager.getDefaultVolume());
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

    private static void ejectResin(World world, BlockPos pos, EnumFacing side, int quantity) {
        double ejectBias = 0.3D;
        double ejectX = (double) pos.getX() + 0.5D + (double) side.getFrontOffsetX() * 0.3D;
        double ejectY = (double) pos.getY() + 0.5D + (double) side.getFrontOffsetY() * 0.3D;
        double ejectZ = (double) pos.getZ() + 0.5D + (double) side.getFrontOffsetZ() * 0.3D;

        for (int i = 0; i < quantity; ++i) {
            EntityItem entityitem = new EntityItem(world, ejectX, ejectY, ejectZ, ItemName.misc_resource.getItemStack(MiscResourceType.resin));
            entityitem.setDefaultPickupDelay();
            world.spawnEntity(entityitem);
        }

    }

    public boolean canBeStoredInToolbox(ItemStack itemstack) {
        return true;
    }

    @Override
    public String getModelResourcePath() {
        return "tool/treetap";
    }

    /**
     * Get custom resource name.
     * To use default resource name, return null.
     *
     */
    @Override
    public String getModelResourceName(int meta) {
        return null;
    }
}
