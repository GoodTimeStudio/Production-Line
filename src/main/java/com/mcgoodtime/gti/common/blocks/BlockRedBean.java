package com.mcgoodtime.gti.common.blocks;

import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;

import java.util.List;
import java.util.Random;

import com.mcgoodtime.gti.common.core.Gti;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedBean extends BlockBush {
	public BlockRedBean() {
		super(Material.plants);
		this.setBlockName("Gti.Block.RedBean");
		this.setCreativeTab(creativeTabGti);
		this.setBlockTextureName(Gti.RESOURCE_DOMAIN + ":" + "BlockRedBean");
		this.setHardness(0.0F);
		this.setResistance(0.0F);
		float f = 0.5F;
        float f1 = 0.015625F;
		this.setStepSound(soundTypeGrass);
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
	}

	public int getRenderType() {
		return 23;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
	}

	@Override
	protected boolean canPlaceBlockOn(Block placedOn) {
		return placedOn == Blocks.clay;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return y >= 0 && y < 256
				&& world.getBlock(x, y - 1, z).getMaterial() == Material.clay
				&& world.getBlockMetadata(x, y - 1, z) == 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
			int y, int z) {
		return AxisAlignedBB.getBoundingBox((double) x + this.minX, (double) y
				+ this.minY, (double) z + this.minZ, (double) x + this.maxX,
				(double) y + this.maxY, (double) z + this.maxZ);
	}

	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_,
			int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
			List p_149743_6_, Entity p_149743_7_) {
		if (p_149743_7_ == null || !(p_149743_7_ instanceof EntityBoat)) {
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_,
					p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_,
					p_149743_7_);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 2129968;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_) {
		return 2129968;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_,
			int p_149720_3_, int p_149720_4_) {
		return 2129968;

	}
}
