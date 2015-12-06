package com.mcgoodtime.gti.common.blocks.generator;

import com.mcgoodtime.gti.common.blocks.BlockContainerGti;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.tiles.TileAdvSolar;
import com.mcgoodtime.gti.common.tiles.TileGti;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
public class BlockAdvSolar extends BlockContainerGti {

    public BlockAdvSolar() {
        super(Material.iron, "AdvSolar");
    }

    /**
     * Called upon blocks activation (right click on the blocks.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p1, float p2, float p3, float p4) {
        if (!world.isRemote) {
            entityPlayer.openGui(Gti.instance, GuiHandler.EnumGui.AdvSolar.ordinal(), world, x, y, z);
        } else {
            entityPlayer.isInvisibleToPlayer(entityPlayer);
        }
        return true;
    }

    @Override
    protected Class<? extends TileGti> getTileEntityClass() {
        return TileAdvSolar.class;
    }
}
