package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 */
public class ItemOre extends ItemMulti {

    public ItemOre() {
        super("ore");

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
        list.add("ingot_iridium");
        list.add("diamond_plate");
        list.add("dense_diamond_plate");
        list.add("crushed_iridium");
        list.add("cleaned_crushed_iridium");
        list.add("dust_iridium");
        list.add("small_dust_iridium");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "ore";
    }
}
