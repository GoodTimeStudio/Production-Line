/*
package com.mcgoodtime.productionline.common.blocks.generator;

import com.mcgoodtime.productionline.common.blocks.BlockContainerPL;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 *//*
public class BlockAdvSolar extends BlockContainerPL {

    public BlockAdvSolar() {
        super(Material.iron, "AdvSolar");
    }

    /**
     * Called upon blocks activation (right click on the blocks.)
     *//*
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p1, float p2, float p3, float p4) {
        if (!world.isRemote) {
            entityPlayer.openGui(ProductionLine.instance, GuiHandler.EnumGui.AdvSolar.ordinal(), world, x, y, z);
        } else {
            entityPlayer.isInvisibleToPlayer(entityPlayer);
        }
        return true;
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass() {
        return TileAdvSolar.class;
    }
}*/
