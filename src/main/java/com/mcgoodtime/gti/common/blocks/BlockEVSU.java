package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by suhao on 2015-6-3-0003.
 */
public class BlockEVSU extends BlockContainer {

    protected BlockEVSU() {
        super(Material.iron);
        this.setCreativeTab(Gti.creativeTabGti);
        this.setHardness(1.5f);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("gti.block.EVSU");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEVSU();
    }
}
