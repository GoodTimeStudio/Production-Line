package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.core.GuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;
import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;
import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;

/*
 * Created by suhao on 2015.7.10.
 */

public class BlockHeatDryer extends BlockContainer {

    protected BlockHeatDryer(Material material, String name) {
        super(material);
        this.setBlockName(MOD_ID + "." + "block" + "." + name);
        this.setBlockTextureName(RESOURCE_DOMAIN + ":" + "block" + name);
        this.setCreativeTab(creativeTabGti);
        GameRegistry.registerBlock(this, name);
        GtiConfig.gtiLogger.log(Level.INFO, name + Integer.toString(Block.getIdFromBlock(this)));
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }

    /**
     * Called upon blocks activation (right click on the blocks.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p1, float p2, float p3, float p4) {
        if (!world.isRemote) {
            entityPlayer.openGui(Gti.instance, GuiHandler.EnumGui.HeatDryer.ordinal(), world, x, y, z);
        } else {
            entityPlayer.isInvisibleToPlayer(entityPlayer);
        }
        return true;
    }
}
