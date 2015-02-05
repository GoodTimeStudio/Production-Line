package goodtime.mod.Industrial.common.block;

<<<<<<< HEAD:src/main/java/goodtime/mod/Industrial/core/Ore.java
import javax.tools.Tool;

import goodtime.mod.Industrial.CreativeTabGTI;
import cpw.mods.fml.common.Mod.EventHandler;
=======
import cpw.mods.fml.common.Mod;
>>>>>>> origin/master:src/main/java/goodtime/mod/Industrial/common/block/Ore.java
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
<<<<<<< HEAD:src/main/java/goodtime/mod/Industrial/core/Ore.java
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock)
	.setBlockName("“øøÛ Ø")
	.setHardness(5)
	.setCreativeTab(CreativeTabGTI.tab)
	.setBlockTextureName("gti:blockOreIR")
	.setResistance(20);
	
=======

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock).setBlockName("“øÔøΩÔøΩ Ø");
>>>>>>> origin/master:src/main/java/goodtime/mod/Industrial/common/block/Ore.java
	
	protected Ore(Material str) {
		super(str);
	}


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    	 GameRegistry.registerBlock(IR, "iridiumore");
    	 
	}
	
<<<<<<< HEAD:src/main/java/goodtime/mod/Industrial/core/Ore.java
    static {//◊¢≤·∑ΩøÈ
    	Block.blockRegistry.addObject(1000, "IROre", IR);	
    }
    
    static {//◊¢≤·”Ô—‘
    	LanguageRegistry.addName(IR, "“øøÛ Ø");
=======
    static {
    	Block.blockRegistry.addObject(1000, "IROre", IR);
    	IR.setCreativeTab(CreativeTabGTI.tab);
    	IR.setBlockTextureName("gti:blockOreIR");
    	LanguageRegistry.addName(IR, "“øÔøΩÔøΩ Ø");
>>>>>>> origin/master:src/main/java/goodtime/mod/Industrial/common/block/Ore.java
    }
    
    static {//◊¢≤·∆‰À˚ Ù–‘
    	IR.setHarvestLevel("pickaxe" , 3);
    }
	
}
