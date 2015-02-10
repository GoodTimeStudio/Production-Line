package goodtime.mod.Industrial.common.Machine.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goodtime.mod.Industrial.common.TileEntity.TileEntityGenGasKU;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import goodtime.mod.Industrial.common.core.Industrial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class Generator extends BlockContainer{

	public static Block GasKU = new Generator(Material.iron)
	.setBlockName("GasKineticGen")
	.setHardness(2)
	.setCreativeTab(CreativeTabGTI.tab);
	
	protected Generator(Material str) {
		super(str);
	}

	public static void preInit() {
		GameRegistry.registerBlock(GasKU, "GasKineticGen");
	}
	
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityGenGasKU.class, Industrial.MODID);
	}
	
	
	//========================================================
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		player.openGui(Industrial.modInstance, 0, world, x, y, z);
		return true;
	};
	
	
	
	
	
	
 	
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side ,int p)
	{
		if (side == 2) {
			return back;
		}
		else if (side == 3) {
			return front;
		} 
		else {
			return blockIcon;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iicr)
	{
	this.blockIcon = iicr.registerIcon("gti:GenGasKU");
	this.front = iicr.registerIcon("gti:GenGasKU_front");
	this.back = iicr.registerIcon("gti:GenGasKU_back");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGenGasKU();
	}
}
