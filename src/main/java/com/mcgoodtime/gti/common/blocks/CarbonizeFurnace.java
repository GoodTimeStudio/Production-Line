package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author suhao
 */
public class CarbonizeFurnace extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon field_149935_N;
    @SideOnly(Side.CLIENT)
    private IIcon field_149936_O;

    public CarbonizeFurnace() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabGti.creativeTabGti);
        this.setBlockName("gti.block.CarbonizeFurnace");
        GameRegistry.registerBlock(this, "CarbonizeFurnace");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCarbonizeFurnace();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.field_149935_N : (p_149691_1_ == 0 ? this.field_149935_N : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.field_149936_O));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon("furnace_side");
        this.field_149936_O = p_149651_1_.registerIcon("furnace_front_off");
        this.field_149935_N = p_149651_1_.registerIcon("furnace_top");
    }
}
