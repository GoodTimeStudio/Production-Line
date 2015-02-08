package goodtime.mod.Industrial.common.Machine.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import goodtime.mod.Industrial.common.core.Industrial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class Generator extends Block{

	public static Block GasKU = new Generator(Material.iron)
	.setBlockName("GasKineticGen")
	.setHardness(2)
	.setCreativeTab(CreativeTabGTI.tab)
	.setBlockTextureName("gti:GenGasKU");
	
	protected Generator(Material str) {
		super(str);
	}

	public static void preInit() {
		GameRegistry.registerBlock(GasKU, "GasKineticGen");
	}
	
	static {//注册方块
    	Block.blockRegistry.addObject(1001, "GasKineticGen", GasKU);	
    	
    }

}
