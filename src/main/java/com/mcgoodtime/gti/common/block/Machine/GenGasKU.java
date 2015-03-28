package com.mcgoodtime.gti.common.block.Machine;

import com.mcgoodtime.gti.client.gui.GUIGenGasKU;
import com.mcgoodtime.gti.common.TileEntity.TileEntityGenGasKU;
import com.mcgoodtime.gti.common.container.ContainerGenGasKU;
import com.mcgoodtime.gti.common.core.GTI;
import com.mcgoodtime.gti.common.core.GuiHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GenGasKU extends BlockContainer {

    private IIcon[] icons = new IIcon[6];

    public static Block GenGasKU = new GenGasKU(Material.iron)
	.setBlockName("gti.GenGasKU")
	.setCreativeTab(CreativeTabs.tabFood)
	.setHardness(2.0f);

	protected GenGasKU(Material str) {
		super(str);
	}

	public static void preInit() {
		GameRegistry.registerBlock(GenGasKU, "GenGasKU");
	}

    //创建一个新的TileEntity
    @Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGenGasKU();
	}

    //当方块被激活时（右键）
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
			int s, float p1, float p2, float p3) {
        if(world.isRemote) {
            if (world.getTileEntity(x, y, z) != null)
                player.openGui(GTI.gtiInstance, GuiHandler.GUIs.GenGasKU.ordinal(), world, x, y, z);
            return true;
        }
        return true;
	}


//************************************************************
//***************注册材质*************************************
	
	@SideOnly(Side.CLIENT)	private static IIcon front;
	@SideOnly(Side.CLIENT)	private static IIcon back;
	/*@SideOnly(Side.CLIENT)	private static IIcon left;
	@SideOnly(Side.CLIENT)	private static IIcon right;
	@SideOnly(Side.CLIENT)	private static IIcon top;
	@SideOnly(Side.CLIENT)	private static IIcon low;*/

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		super.registerBlockIcons(icon);
		this.blockIcon = icon.registerIcon("gti:GenGasKU");
		this.front = icon.registerIcon("gti:GenGasKU_front");
		this.back = icon.registerIcon("gti:GenGasKU_back");
	}

    /**
     *  side:
     *	2 is North(back)	3 is South(front)
     *	5 is East(left)	4 is West(right)
     *	1 is up(top)	0 is down(low)
     *	main:3-4
     */
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 3) {
			return this.front;
		} else if (side == 2) {
			return this.back;
		} else {
			return this.blockIcon;
		}
	}
//*****************************************************************************
}
