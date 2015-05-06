package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author suhao
 */
public class BlockCarbonizeFurnace extends BlockContainer {

    private boolean isBurn;

    @SideOnly(Side.CLIENT)
    private IIcon sideFront;
    @SideOnly(Side.CLIENT)
    private IIcon sideLeft;
    @SideOnly(Side.CLIENT)
    private IIcon sideTop;
    @SideOnly(Side.CLIENT)
    private IIcon sideLow;

    public BlockCarbonizeFurnace(boolean b) {
        super(Material.gourd);
        this.isBurn = b;
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabGti.creativeTabGti);
        this.setBlockName("gti.block.BlockCarbonizeFurnace");
        GameRegistry.registerBlock(this, "BlockCarbonizeFurnace");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.sideTop : (side == 0 ? this.sideLow : (side != meta ? this.blockIcon : sideFront));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p1, float p2, float p3, float p4) {
        if (world.isRemote) {
            entityPlayer.openGui(Gti.instance, GuiHandler.Guis.CarbonizeFurnace.ordinal(), world, x, y, z);
        } else {
            entityPlayer.isInvisibleToPlayer(entityPlayer);
        }
        return true;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (p_149689_6_.hasDisplayName()) {
            ((TileCarbonizeFurnace)world.getTileEntity(x, y, z)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iir) {
        this.sideFront = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_front_" + (isBurn ? "on" : "off"));
        this.sideTop = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_top_" + (isBurn ? "on" : "off"));
        this.sideLow = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_low");
        this.blockIcon = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_side");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCarbonizeFurnace();
    }
}
