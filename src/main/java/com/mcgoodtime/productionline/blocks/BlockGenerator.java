/*
 * This file is part of Production Line, licensed under MIT License (MIT).
 *
 * Copyright (c) 2020 GoodTime Studio <https://github.com/GoodTimeStudio>
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

import com.mcgoodtime.productionline.core.GuiHandler;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.init.PLItems;
import com.mcgoodtime.productionline.tiles.TefnutTear;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * The energy generator block
 * Created by NightOwl on 2020/7/17
 *
 * @author NightOwl
 * @since 0.6
 */
public class BlockGenerator extends BlockContainerPL implements IMultiIDBlock<PropertyEnum<BlockGenerator.Type>> {

    public static final PropertyEnum<BlockGenerator.Type> PROPERTY_TYPE = PropertyEnum.create("type", BlockGenerator.Type.class);

    public enum Type implements IStringSerializable, IBlockType {
        WATER_GENERATOR("tefnut_tear", TefnutTear.class, null);

        private final String name;
        public final Class<? extends TileEntity> tileClass;
        public final GuiHandler.EnumGui gui;

        Type(String name, Class<? extends TileEntity> tileClass, GuiHandler.EnumGui gui) {
            this.name = name;
            this.tileClass = tileClass;
            this.gui = gui;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getTypeName() {
            return this.name;
        }
    }

    public BlockGenerator() {
        super(Material.IRON, "generator");
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_TYPE, Type.WATER_GENERATOR));
        for (BlockGenerator.Type t : BlockGenerator.Type.values()) {
            GameRegistry.registerTileEntity(t.tileClass, new ResourceLocation(ProductionLine.MOD_ID, t.getTypeName()));
        }
    }

    @Override
    protected Class<? extends TileEntity> getTileEntityClass(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).tileClass;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.getMetadata() < BlockMachine.Type.values().length) {
            world.setBlockState(pos, state.withProperty(PROPERTY_TYPE, BlockGenerator.Type.values()[stack.getMetadata()]), 2);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY_TYPE);
    }

    @Nonnull
    @Override
    public PropertyEnum getBlockTypeContainer() {
        return PROPERTY_TYPE;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).ordinal();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta < BlockGenerator.Type.values().length) {
            return this.getDefaultState().withProperty(PROPERTY_TYPE, BlockGenerator.Type.values()[meta]);
        }
        return null;
    }

    //Open GUI when player rightClick with sheepCrook
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){

        if(worldIn.isRemote){
            if(playerIn.getHeldItemMainhand().getItem() == PLItems.sheepCrook){
                //TODO addGUI
//                playerIn.openGui();
            }
        }
        return true;
    }
}
