package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 */
public class ItemCrafting extends ItemMulti {

    public ItemCrafting() {
        super("crafting");
        PLItems.eyeOfTheDesert = this.next();
        PLItems.aquamarine = this.next();


    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<>();
        list.add("eye_of_the_desert");
        list.add("aquamarine");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "crafting";
    }
}

