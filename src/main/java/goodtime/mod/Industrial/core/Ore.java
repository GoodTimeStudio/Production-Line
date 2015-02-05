package goodtime.mod.Industrial.core;

import javax.tools.Tool;

import goodtime.mod.Industrial.CreativeTabGTI;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock)
	.setBlockName("Ò¿¿óÊ¯")
	.setHardness(5)
	.setCreativeTab(CreativeTabGTI.tab)
	.setBlockTextureName("gti:blockOreIR")
	.setResistance(20);
	
	
	protected Ore(Material str) {
		super(str);
	}


    @EventHandler 
    public static void preInit(FMLPreInitializationEvent event) {
    	 GameRegistry.registerBlock(IR, "IROre");
    	 
	}
	
    static {//×¢²á·½¿é
    	Block.blockRegistry.addObject(1000, "IROre", IR);	
    }
    
    static {//×¢²áÓïÑÔ
    	LanguageRegistry.addName(IR, "Ò¿¿óÊ¯");
    }
    
    static {//×¢²áÆäËûÊôÐÔ
    	IR.setHarvestLevel("pickaxe" , 3);
    }
	
}
