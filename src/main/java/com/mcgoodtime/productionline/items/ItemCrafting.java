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
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<>();
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "crafting";
    }
}

