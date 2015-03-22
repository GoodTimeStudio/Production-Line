package com.mcgoodtime.gti.common.block.Machine;

import com.mcgoodtime.gti.client.gui.GUIGenGasKU;
import com.mcgoodtime.gti.common.core.CreativeTabGTI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GenGasKU extends BlockContainer{

	public static Block GenGasKU = new GenGasKU(Material.iron)
	.setBlockName("gti.GenGasKU")
	.setCreativeTab(CreativeTabGTI.tab)
	.setHardness(2.0f);

	protected GenGasKU(Material str) {
		super(str);
	}

	public static void preInit() {
		GameRegistry.registerBlock(GenGasKU, "GenGasKU");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
			int s, float p1, float p2, float p3) {
		Minecraft mc = Minecraft.getMinecraft();
		 mc.displayGuiScreen(new GUIGenGasKU(mc.currentScreen));
		return true;
	}
	
	//========================================================
	
	@SideOnly(Side.CLIENT)	private static IIcon front;
	@SideOnly(Side.CLIENT)	private static IIcon back;
	/*@SideOnly(Side.CLIENT)	private static IIcon left;
	@SideOnly(Side.CLIENT)	private static IIcon right;
	@SideOnly(Side.CLIENT)	private static IIcon top;
	@SideOnly(Side.CLIENT)	private static IIcon low;*/
	
	/**
	 *  side:
	 *  0 is null
	 *	2 is North(back)	3 is South(front)
	 *	5 is East(left)	4 is West(right)
	 *	1 is top(top)	6 is low(low)
	 *	main:3-4
	 */
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		super.registerBlockIcons(icon);
		this.blockIcon = icon.registerIcon("gti:GenGasKU");
		this.front = icon.registerIcon("gti:GenGasKU_front");
		this.back = icon.registerIcon("gti:GenGasKU_back");
	}
	
	@Override
	public IIcon getIcon(int side, int v2) {
		if (side == 3) {
			return this.front;
		} else if (side == 2) {
			return this.back;
		} else {
			return this.blockIcon;
		}
	}
}
