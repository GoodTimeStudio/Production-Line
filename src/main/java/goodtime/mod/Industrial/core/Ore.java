package goodtime.mod.Industrial.core;

import goodtime.mod.Industrial.CreativeTabGTI;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock).setBlockName("Ò¿¿óÊ¯");
	
	protected Ore(Material str) {
		super(str);
	}


    @EventHandler 
    public static void preInit(FMLPreInitializationEvent event) {
    	 GameRegistry.registerBlock(IR, "IROre");
    	 
	}
	
    static {
    	Block.blockRegistry.addObject(1000, "IROre", IR);
    	IR.setCreativeTab(CreativeTabGTI.tab);
    	IR.setBlockTextureName("gti:blockOreIR");
    	LanguageRegistry.addName(IR, "Ò¿¿óÊ¯");
    }
	
}
