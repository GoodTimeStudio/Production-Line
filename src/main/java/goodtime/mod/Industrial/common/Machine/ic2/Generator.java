package goodtime.mod.Industrial.common.Machine.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import goodtime.mod.Industrial.common.core.Industrial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class Generator extends Block{

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
	
	@Override
	public int onBlockPlaced(net.minecraft.world.World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		return p_149660_9_;
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
}
