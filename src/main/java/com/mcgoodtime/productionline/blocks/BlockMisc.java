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
package com.mcgoodtime.productionline.blocks;

import com.mcgoodtime.productionline.client.IBlockModelProvider;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.items.ItemBlockPL;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

/**
 * Created by suhao on 2015.10.18.0018.
 *
 * @author suhao
 */
public class BlockMisc extends BlockPL implements IMultiIDBlock<PropertyEnum<BlockMisc.Type>>, IBlockModelProvider {

    public static final PropertyEnum<Type> PROPERTY_TYPE = PropertyEnum.create("type", Type.class);

    public enum Type implements IStringSerializable, IBlockType {;
        //COMPRESSED_WATER_HYACINTH("compressed_water_hyacinth");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }

        @Override
        public String getTypeName() {
            return this.name;
        }
    }

    public BlockMisc() {
        super(Material.ROCK, "block_misc");
        this.setHardness(1.0F);
    }

    @Override
    public ModelResourceLocation getModelResourceLocation(int meta) {
        ResourceLocation res = new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "block_misc");
        return new ModelResourceLocation(res, "type=" + Type.values()[meta]);
    }

    @Nonnull
    @Override
    public PropertyEnum<Type> getBlockTypeContainer() {
        return PROPERTY_TYPE;
    }

    @Override
    protected void registerItemBlock() {
        ForgeRegistries.ITEMS.register(new ItemBlockPL(this));
    }

    /**
     * Convert the BlockState into the correct metadata value
     *
     * @param state
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).ordinal();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     *
     * @param meta
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PROPERTY_TYPE, Type.values()[meta]);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY_TYPE);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(PROPERTY_TYPE, Type.values()[stack.getItemDamage()]));
    }
}
