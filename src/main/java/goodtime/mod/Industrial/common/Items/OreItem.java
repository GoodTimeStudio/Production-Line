package goodtime.mod.Industrial.common.Items;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreItem extends Item {
	
	public static Item CrushedIR = new Item()
	.setUnlocalizedName("·ÛËéµÄÒ¿ï¿½ï¿½Ê¯")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemCrushedIR");
	public static Item DustIR = new Item()
	.setUnlocalizedName("Ò¿ï¿½ï¿½Ê¯·Û")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDustIR");
	public static Item IngotIR = new Item()
	.setUnlocalizedName("Ò¿¶§")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemIngotIR")
	.setMaxStackSize(64);
	
	public static ItemStack IngotIRs = new ItemStack(IngotIR);
	
	@Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    	GameRegistry.registerItem(CrushedIR, "CrushedIriridium" );	 
    	GameRegistry.registerItem(DustIR, "PowderIriridium");
    	GameRegistry.registerItem(IngotIR, "IngotIriridium");
	}
	
	static {
		LanguageRegistry.addName(CrushedIR, "·ÛËéµÄÒ¿ï¿½ï¿½Ê¯");
		LanguageRegistry.addName(DustIR, "Ò¿ï¿½ï¿½Ê¯·Û");
		LanguageRegistry.addName(IngotIR, "Ò¿¶§");
	}
	static float xp;
	static {
		GameRegistry.addSmelting(DustIR, IngotIRs, xp);
	}
	
}
