package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLItems;
import net.minecraft.item.ItemStack;

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
        PLItems.ingotIridium = new ItemStack(this, 1, 0);
        PLItems.diamondPlate = new ItemStack(this, 1, 1);
        PLItems.denseDiamondPlate = new ItemStack(this, 1, 2);
        PLItems.crushedIridium = new ItemStack(this, 1, 3);
        PLItems.cleanedCrushedIridium = new ItemStack(this, 1, 4);
        PLItems.dustIridium = new ItemStack(this, 1, 5);
        PLItems.smallDustIridium = new ItemStack(this, 1, 6);
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
