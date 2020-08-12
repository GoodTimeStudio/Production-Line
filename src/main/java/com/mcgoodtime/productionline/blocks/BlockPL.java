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
package com.mcgoodtime.productionline.blocks;

import com.mcgoodtime.productionline.core.PLConfig;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import static com.mcgoodtime.productionline.core.ProductionLine.*;

/**
 * Created by suhao on 2015-6-10-0010.
 *
 * @author suhao
 */
public class BlockPL extends Block {

    public static final PropertyDirection PROPERTY_FACING = PropertyDirection.create("facing");

    public String internalName;

    public BlockPL(Material material, String name, float hardness, float resistance,
                   String harvestLevelToolClass, int harvestLevel) {
        this(material, name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvestLevelToolClass, harvestLevel);
    }

    public BlockPL(Material material, String name) {
        super(material);
        this.setUnlocalizedName(MOD_ID + ".block." + name);
        this.setCreativeTab(creativeTabPL);
        this.internalName = name;
        this.setRegistryName(new ResourceLocation(MOD_ID, this.internalName));
        ForgeRegistries.BLOCKS.register(this);
        this.registerItemBlock();
        if (this instanceof IOrientableBlock) {
            this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING, EnumFacing.NORTH));
        }
        PLConfig.gtiLogger.log(Level.INFO, name + ":" + Integer.toString(Block.getIdFromBlock(this)));
    }

    /**
     * Register item block.
     * Forge recommend register item block separately.
     */
    protected void registerItemBlock() {
        ItemBlock item = new ItemBlock(this);
        item.setRegistryName(new ResourceLocation(MOD_ID, this.internalName));
        ForgeRegistries.ITEMS.register(item);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        if (this instanceof IOrientableBlock) {
            return new BlockStateContainer(this, PROPERTY_FACING);
        }
        else {
            return super.createBlockState();
        }
    }

    /**
     * Convert the BlockState into the correct metadata value
     *
     * @param state
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        if (this instanceof IOrientableBlock) {
            return state.getValue(PROPERTY_FACING).getIndex();
        }
        else {
            return super.getMetaFromState(state);
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        if (this instanceof IOrientableBlock) {
            EnumFacing enumfacing = EnumFacing.getFront(meta);
            if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
                enumfacing = EnumFacing.NORTH;
            }
            return this.getDefaultState().withProperty(PROPERTY_FACING, enumfacing);
        }
        else {
            return super.getStateFromMeta(meta);
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (this instanceof IOrientableBlock) {
            worldIn.setBlockState(pos, state.withProperty(PROPERTY_FACING, placer.getHorizontalFacing().getOpposite()), 2);
        }
    }
}
