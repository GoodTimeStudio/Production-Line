package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.init.GtiItems;
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
        GtiItems.ingotIridium = new ItemStack(this, 1, 0);
        GtiItems.diamondPlate = new ItemStack(this, 1, 1);
        GtiItems.denseDiamondPlate = new ItemStack(this, 1, 2);
        GtiItems.crushedIridium = new ItemStack(this, 1, 3);
        GtiItems.cleanedCrushedIridium = new ItemStack(this, 1, 4);
        GtiItems.dustIridium = new ItemStack(this, 1, 5);
        GtiItems.smallDustIridium = new ItemStack(this, 1, 6);
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
