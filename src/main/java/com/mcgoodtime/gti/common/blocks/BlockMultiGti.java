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
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileGti;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * Created by suhao on 2015.7.13.
 */
public abstract class BlockMultiGti extends BlockGti {

    @SideOnly(Side.CLIENT)
    protected IIcon[][] textures;

    @SideOnly(Side.CLIENT)
    protected IIcon[] normal = new IIcon[6];
    @SideOnly(Side.CLIENT)
    protected IIcon[] burning = new IIcon[6];
    //top, low, front, back, left, right;

    public BlockMultiGti(Material material, String name, float hardness, float resistance, String harvestLevelToolClass, int harvestLevel) {
        super(material, name, hardness, resistance, harvestLevelToolClass, harvestLevel);
    }

    public BlockMultiGti(Material material, String name) {
        super(material, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iir) {
        for (int i = 0; i < normal.length; ++i) {
            normal[i] = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + blockName + "-" + i);
        }
        if (canBurn()) {
            for (int i = 0; i < burning.length; ++i) {
                burning[i] = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + blockName + "-on-" + i);
            }
        }
    }

    /**
     * World only
     */
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEntity te = iBlockAccess.getTileEntity(x, y, z);
        if (te instanceof TileGti) {
            boolean isBurn = ((TileGti) te).isBurn();
            switch (((TileGti) te).getFacing()) {
                case 2:
                    switch (side) {
                        case 5: return isBurn ? burning[4] : normal[4];
                        case 4: return isBurn ? burning[5] : normal[5];
                        case 2: return isBurn ? burning[2] : normal[2];
                        case 3: return isBurn ? burning[3] : normal[3];
                    }
                case 5:
                    switch (side) {
                        case 3: return isBurn ? burning[4] : normal[4];
                        case 2: return isBurn ? burning[5] : normal[5];
                        case 5: return isBurn ? burning[2] : normal[2];
                        case 4: return isBurn ? burning[3] : normal[3];
                    }
                case 3:
                    switch (side) {
                        case 4: return isBurn ? burning[4] : normal[4];
                        case 5: return isBurn ? burning[5] : normal[5];
                        case 3: return isBurn ? burning[2] : normal[2];
                        case 2: return isBurn ? burning[3] : normal[3];
                    }
                case 4:
                    switch (side) {
                        case 2: return isBurn ? burning[4] : normal[4];
                        case 3: return isBurn ? burning[5] : normal[5];
                        case 4: return isBurn ? burning[2] : normal[2];
                        case 5: return isBurn ? burning[3] : normal[3];
                    }
            }

            if (side == 1) {
                return isBurn ? burning[0] : normal[0];
            }
            if (side == 0) {
                return isBurn ? burning[1] : normal[1];
            }
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
    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 3: return normal[2];
            case 2: return normal[3];
            case 5: return normal[5];
            case 4: return normal[4];
            case 1: return normal[0];
            case 0: return normal[1];
            default: return null;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileGti) {
            if(entityLivingBase == null) {
                ((TileGti) te).setFacing((short) 2);
            } else {
                int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                switch(l) {
                    case 0:
                        ((TileGti) te).setFacing((short) 2);
                        break;
                    case 1:
                        ((TileGti) te).setFacing((short) 5);
                        break;
                    case 2:
                        ((TileGti) te).setFacing((short) 3);
                        break;
                    case 3:
                        ((TileGti) te).setFacing((short) 4);
                }
            }
        }
    }

    public boolean canBurn() {
        return false;
    }

    protected String getTextureName(int index) {
        Item item = Item.getItemFromBlock(this);
        if(!item.getHasSubtypes()) {
            return index == 0 ? this.getUnlocalizedName() : null;
        } else {
            ItemStack itemStack = new ItemStack(this, 1, index);
            String ret = item.getUnlocalizedName(itemStack);
            return ret == null ? null : ret.substring(4).replace("item", "block");
        }
    }

}
