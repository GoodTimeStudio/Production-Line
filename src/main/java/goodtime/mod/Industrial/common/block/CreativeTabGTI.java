package goodtime.mod.Industrial.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabGTI extends CreativeTabs {
	
	private int icon;
	public static CreativeTabGTI tab ;
	
	public CreativeTabGTI(int icon, String label) { 
		 super(label); 
		 this.icon = icon; 
	} 

	@Override 
	public ItemStack getIconItemStack() {
		Item iconItem;
		iconItem = GameRegistry.findItem("minecraft", "stone");
		return new ItemStack(iconItem);
	}

	
	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Ore.IR);
	}
	/*
	static {
		//LanguageRegistry.addName(CreativeTabGTI.tab, "GoodTime Industrial");
	}*/
}
