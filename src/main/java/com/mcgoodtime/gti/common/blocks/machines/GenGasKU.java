package com.mcgoodtime.gti.common.blocks.machines;

import com.mcgoodtime.gti.common.tiles.TileGenGasKu;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GenGasKU extends BlockContainer {

    public static Block GenGasKU = new GenGasKU(Material.rock);

    protected GenGasKU(Material meta) {
        super(Material.rock);
        setCreativeTab(Gti.creativeTabGTI);
        setBlockName("GenGasKU");
        setBlockTextureName("gti:GenGasKU");
    }

    /**
     * Called upon blocks activation (right click on the blocks.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p1, float p2, float p3, float p4) {
        if (world.isRemote) {
            entityPlayer.openGui(Gti.gtiInstance, GuiHandler.GUIs.GenGasKU.ordinal(), world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileGenGasKu();
    }
}