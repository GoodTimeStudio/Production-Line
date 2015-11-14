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
package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.tiles.TileGti;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.block.BlockTextureStitched;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * Created by suhao on 2015.7.13.
 */
public class BlockMultiTexture extends BlockGti implements ITileEntityProvider {

    private static final int[][] facingAndSideToSpriteOffset = new int[][]{{3, 5, 1, 0, 4, 2}, {5, 3, 1, 0, 2, 4}, {0, 1, 3, 5, 4, 2}, {0, 1, 5, 3, 2, 4}, {0, 1, 2, 4, 3, 5}, {0, 1, 4, 2, 5, 3}};
    protected IIcon textures[];
    //top, low, front, back, left, right;

    public BlockMultiTexture(Material material, String name, float hardness, float resistance, String harvestLevelToolClass, int harvestLevel) {
        super(material, name, hardness, resistance, harvestLevelToolClass, harvestLevel);
        GameRegistry.registerTileEntity(this.getTileEntityClass(), name);
    }

    public BlockMultiTexture(Material material, String name) {
        super(material, name);
        GameRegistry.registerTileEntity(this.getTileEntityClass(), name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iir) {
        this.textures = new IIcon[12];

        for(int burn = 0; burn < 2; ++burn) {
            for(int side = 0; side < 6; ++side) {
                int subIndex = burn * 6 + side;
                String name = Gti.RESOURCE_DOMAIN + ":" + "block" + blockName + ":" + subIndex;
                BlockTextureStitched texture = new BlockTextureStitched(name, subIndex);
                this.textures[subIndex] = texture;
                ((TextureMap)iir).setTextureEntry(name, texture);
            }
        }
    }

    /**
     * World only
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        if (iBlockAccess.getTileEntity(x, y, z) instanceof TileGti) {
            int facing = ((TileGti) iBlockAccess.getTileEntity(x, y, z)).facing;
            boolean isBurn = ((TileGti) iBlockAccess.getTileEntity(x, y, z)).active;

            int i = facingAndSideToSpriteOffset[facing][side];
            if (isBurn) {
                i += 6;
            }

            return textures[i];
        }
        return null;
    }

    /**
     * Hand only
     *
     * side:
     * 1:top  5:east  3:south
     * 0:low  4:west  2:north
     *
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return textures[facingAndSideToSpriteOffset[3][side]];
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileGti) {
            if(entityLivingBase == null) {
                ((TileGti) te).facing = ((short) 2);
            } else {
                int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                switch(l) {
                    case 0:
                        ((TileGti) te).facing = ((short) 2);
                        break;
                    case 1:
                        ((TileGti) te).facing = ((short) 5);
                        break;
                    case 2:
                        ((TileGti) te).facing = ((short) 3);
                        break;
                    case 3:
                        ((TileGti) te).facing = ((short) 4);
                }
            }
        }
    }

    protected Class<? extends TileGti> getTileEntityClass() {
        return TileGti.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var) {
        if (this.getTileEntityClass() != null) {
            try {
                return this.getTileEntityClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
