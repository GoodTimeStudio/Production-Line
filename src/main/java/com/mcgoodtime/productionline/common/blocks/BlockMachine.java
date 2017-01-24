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

package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockPL;
import com.mcgoodtime.productionline.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.productionline.common.tiles.TileHeatDryer;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import ic2.api.item.IC2Items;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mcgoodtime.productionline.common.core.ProductionLine.*;

/**
 * Created by BestOwl on 2015.11.22.0022.
 *
 * @author BestOwl
 * @since 0.2
 */
public class BlockMachine extends BlockContainerPL implements IMultiMetaBlock {
    private static List<String> internalNameList = new ArrayList<>();
    private PropertyEnum<Type> property = PropertyEnum.create("type", Type.class);

    static {
        for (Type type : Type.values()) {
            internalNameList.add(type.getName());
        }
    }

    public BlockMachine() {
        super(Material.IRON, "BlockMachine");
        this.setHardness(2.0F);
        for (int i = 0; i < this.getMaxMeta(); i++) {
            GameRegistry.registerTileEntity(this.getTileEntityClass(i), internalNameList.get(i));
        }

        PLBlocks.carbonizeFurnace = new ItemStack(this, 1, 0);
        PLBlocks.heatDryer = new ItemStack(this, 1, 1);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer.Builder(this)
                .add(property)
                .build();
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockPL.class;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            GuiHandler.EnumGui gui = this.getGui(state.getValue(property));
            if (gui != null) {
                player.openGui(ProductionLine.getInstance(), gui.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }


    private GuiHandler.EnumGui getGui(Type type) {
        switch (type) {
            case CARBONIZE_FURNACE:
                return GuiHandler.EnumGui.CarbonizeFurnace;
            case HEAT_DRYER:
                return GuiHandler.EnumGui.HeatDryer;
            default:
                return null;
        }
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass(int meta) {
        switch (meta) {
            case 0:
                return TileCarbonizeFurnace.class;
            case 1:
                return TileHeatDryer.class;
            default:
                return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        switch (state.getValue(property)) {
            case CARBONIZE_FURNACE:
                TileEntity te = world.getTileEntity(pos);
                if (!(te instanceof TilePL)) {
                    return;
                }
                if (((TilePL) te).active) {
                    float fmod;
                    float f1mod;
                    float f2mod;

                    float f = (float) pos.getX() + 1.0F;
                    float f1 = (float) pos.getY() + 1.0F;
                    float f2 = (float) pos.getZ() + 1.0F;

                    for (int i = 0; i < 4; ++i) {
                        fmod = -0.2F - random.nextFloat() * 0.6F;
                        f1mod = -0.1F + random.nextFloat() * 0.2F;
                        f2mod = -0.2F - random.nextFloat() * 0.6F;
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (f + fmod), (double) (f1 + f1mod), (double) (f2 + f2mod), 0.0D, 0.0D, 0.0D);
                    }
                }
                break;

            default:
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return IC2Items.getItem("machine").getItem();
    }

    @Override
    public int getMaxMeta() {
        return internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this block. This version accepts an ItemStack so different stacks can have
     * different names based on their meta or NBT.
     */
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(itemStack.getItemDamage());
    }

    /**
     * Get block's unlocalized name
     *
     * @return unlocalized name
     */
    @Override
    public String getBlockName(int meta) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(meta);
    }

    @Override
    public String getInternalName(int meta) {
        return internalNameList.get(meta);
    }

    public enum Type implements IStringSerializable {
        CARBONIZE_FURNACE("CarbonizeFurnace"),
        HEAT_DRYER("HeatDryer"),
        ;

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }
    }
}
