package goodtime.mod.Industrial.common.block;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock).setBlockName("ҿ��ʯ");
	
	protected Ore(Material str) {
		super(str);
	}


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    	 GameRegistry.registerBlock(IR, "iridiumore");
    	 
	}
	
    static {
    	Block.blockRegistry.addObject(1000, "IROre", IR);
    	IR.setCreativeTab(CreativeTabGTI.tab);
    	IR.setBlockTextureName("gti:blockOreIR");
    	LanguageRegistry.addName(IR, "ҿ��ʯ");
    }
	
}
