package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 */
public class ItemOre extends ItemMultiDamage {

    public ItemOre() {
        super("ItemOre");
//        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(ProductionLine.RESOURCE_DOMAIN + ":" + this.getInternalName(1), null));
//        for (int i = 0; i < this.getMaxDamage(); i++) {
//            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(ProductionLine.RESOURCE_DOMAIN + ":" + "ItemOre", null));
//        }
//        PLItemModelLoader.instance.register();
        PLItems.ingotIridium = this.next();
        PLItems.diamondPlate = this.next();
        PLItems.denseDiamondPlate = this.next();
        PLItems.crushedIridium = this.next();
        PLItems.cleanedCrushedIridium = this.next();
        PLItems.dustIridium = this.next();
        PLItems.smallDustIridium = this.next();
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<String>();
        list.add("IngotIridium");
        list.add("DiamondPlate");
        list.add("DenseDiamondPlate");
        list.add("CrushedIridium");
        list.add("CleanedCrushedIridium");
        list.add("DustIridium");
        list.add("SmallDustIridium");
        return list;
    }
}
