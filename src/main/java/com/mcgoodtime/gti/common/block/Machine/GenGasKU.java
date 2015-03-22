package com.mcgoodtime.gti.common.block.Machine;

import com.mcgoodtime.gti.client.gui.GUIGenGasKU;
import com.mcgoodtime.gti.common.TileEntity.TileEntityGenGasKU;
import com.mcgoodtime.gti.common.container.ContainerGenGasKU;
import com.mcgoodtime.gti.common.core.CreativeTabGTI;
import com.mcgoodtime.gti.common.core.GTI;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
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

public class GenGasKU extends BlockContainer implements IGuiHandler{

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

    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(com.mcgoodtime.gti.common.core.GTI.class, this);
    }

	@Override
    //创建一个新的TileEntity
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGenGasKU();
	}
	
	@Override
    //当方块被激活时（右键）
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
			int s, float p1, float p2, float p3) {
		player.openGui(GTI.modInstance, 10, world, x, y, z);
		return true;
	}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 10:
                return new GUIGenGasKU((ContainerGenGasKU)player.inventoryContainer, (TileEntityGenGasKU)player.getEntityWorld().getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
	
//************************************************************
//***************注册材质*************************************
	
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
//*****************************************************************************
}
