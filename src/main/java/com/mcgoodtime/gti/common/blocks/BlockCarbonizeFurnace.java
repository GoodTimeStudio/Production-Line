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
import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by suhao on 2015.6.23.
 *
 * @author suhao
 */
public class BlockCarbonizeFurnace extends BlockContainerGti {

    private final Random random = new Random();
    private String name;

    private boolean isBurn;
    private static boolean field_149934_M;

    @SideOnly(Side.CLIENT)
    private IIcon top;
    private IIcon low;
    private IIcon front;
    private IIcon left;

    private short facing;

    public BlockCarbonizeFurnace(Material material, String name, boolean isBurn) {
        super(material, name);
        this.isBurn = isBurn;
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
        switch(side){
            case 0: return this.low;
            case 1: return this.top;
            case 3: return this.front;
            case 4: return this.left;
            default: return this.blockIcon;
        }
    }

    /**
     * World only
     *
     * side:
     * 1:top  5:east  3:south
     * 0:low  4:west  2:north
     *
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEntity tile = iBlockAccess.getTileEntity(x, y, z);

        if (tile instanceof TileCarbonizeFurnace) {
            switch (this.facing) {
                case 2://South
                    switch (side) {
                        case 0: return this.low;
                        case 1: return this.top;
                        case 2: return this.front;
                        case 5: return this.left;
                        default: return this.blockIcon;
                    }
                case 3://North
                    switch (side) {
                        case 0: return this.low;
                        case 1: return this.top;
                        case 3: return this.front;
                        case 4: return this.left;
                        default: return this.blockIcon;
                    }
                case 4://East
                    switch (side) {
                        case 0: return this.low;
                        case 1: return this.top;
                        case 4: return this.front;
                        case 2: return this.left;
                        default: return this.blockIcon;
                    }
                case 5://West
                    switch (side) {
                        case 0: return this.low;
                        case 1: return this.top;
                        case 5: return this.front;
                        case 3: return this.left;
                        default: return this.blockIcon;
                    }
                default://Unknown
                    return this.blockIcon;
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iir) {
        this.top = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + "CarbonizeFurnace" + "_top_" + (isBurn ? "on" : "off"));
        this.low = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + "CarbonizeFurnace" + "_low");
        this.front = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + "CarbonizeFurnace" + "_front_" + (isBurn ? "on" : "off"));
        this.left = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + "CarbonizeFurnace" + "_left_"  + (isBurn ? "on" : "off"));
        this.blockIcon = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "block" + "CarbonizeFurnace");
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

    /**
     * Update which block the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean isBurn, World world, int x, int y, int z)
    {

        TileCarbonizeFurnace tileentity = (TileCarbonizeFurnace) world.getTileEntity(x, y, z);
        field_149934_M = true;

        if (isBurn) {
            world.setBlock(x, y, z, GtiBlocks.litCarbonizeFurnace);

        }
        else {
            world.setBlock(x, y, z, GtiBlocks.carbonizeFurnace);
        }

        field_149934_M = false;


        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCarbonizeFurnace();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack){
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileCarbonizeFurnace) {
            TileCarbonizeFurnace furnace = (TileCarbonizeFurnace)tile;
            //NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);
            if (entityliving == null) {
                this.facing = convertIntegerToShort(5);
            } else {
                this.facing = convertIntegerToShort(BlockPistonBase.determineOrientation(world, x, y, z, entityliving));
            }
            furnace.setFacing(this.facing);
        }
    }

    private static short convertIntegerToShort(int integer_n) {
        return new Integer(integer_n).shortValue();
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!field_149934_M)
        {
            TileCarbonizeFurnace tileentity = (TileCarbonizeFurnace)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

            if (tileentity != null)
            {
                for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1)
                {
                    ItemStack itemstack = tileentity.getStackInSlot(i1);

                    if (itemstack != null)
                    {
                        float f = this.random.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int j1 = this.random.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize)
                            {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
                            p_149749_1_.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
            }
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random1)
    {
        if (this.isBurn)
        {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5F;
            float f1 = (float)y + 0.0F + random1.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)z + 0.5F;
            float f3 = 0.52F;
            float f4 = random1.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                world.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                world.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                world.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory((IInventory) p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
    }

    public static class BlockLitCarbonizeFurnace extends BlockCarbonizeFurnace {

        public BlockLitCarbonizeFurnace(Material material, String name, boolean isBurn) {
            super(material, name, isBurn);
            this.setLightLevel(0.875F);
            this.setCreativeTab(null);
        }
    }
}
