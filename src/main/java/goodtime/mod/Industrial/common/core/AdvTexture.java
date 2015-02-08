package goodtime.mod.Industrial.common.core;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdvTexture {

	@SideOnly(Side.CLIENT)	private static IIcon front;
	@SideOnly(Side.CLIENT)	private static IIcon back;
	@SideOnly(Side.CLIENT)	private static IIcon left;
	@SideOnly(Side.CLIENT)	private static IIcon right;
	@SideOnly(Side.CLIENT)	private static IIcon top;
	@SideOnly(Side.CLIENT)	private static IIcon low;

	/**
	 *  side:
	 *  0 is null
	 *	2 is North(back)	3 is South(front)
	 *	5 is East(left)	4 is West(right)
	 *	1 is top(top)	6 is low(low)
	 *	main:3-4
	 */
	
	static IIcon icon;
	public static void registerBlockIcons(int side, String TextureName)
	{
		IIconRegister iicr = new IIconRegister() {
			@Override
			public IIcon registerIcon(String p_94245_1_) {
				return null;
			}
		};
		AdvTexture.icon = getIcon(side);
		icon = iicr.registerIcon(TextureName);
	}
	
	public static IIcon getIcon(int side)
	{	
		if (side == 0) {
			//return blockIcon;
		} 
		else {
			switch (side) {
				case 2: {
					return back;
				}
				case 3: {
					return front;
				}
				case 5: {
					return left;
				}
				case 4: {
					return right;
				}
				case 1: {
					return top;
				}
				case 6: {
					return low;
				}
			}
		}
		return null;
	}
}
