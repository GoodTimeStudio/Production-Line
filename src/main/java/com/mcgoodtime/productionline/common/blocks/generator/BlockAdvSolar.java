package com.mcgoodtime.productionline.common.blocks.generator;

import com.mcgoodtime.productionline.common.blocks.BlockContainerPL;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
public class BlockAdvSolar extends BlockContainerPL {

    public BlockAdvSolar() {
        super(Material.IRON, "AdvSolar");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ProductionLine.getInstance(), GuiHandler.EnumGui.AdvSolar.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass() {
        return TileAdvSolar.class;
    }
}
