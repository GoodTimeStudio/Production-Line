package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLItems;

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
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<String>();
        list.add("ingot_iridium");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "ore";
    }
}
