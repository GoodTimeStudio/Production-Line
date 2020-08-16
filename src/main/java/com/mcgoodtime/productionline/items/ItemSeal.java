package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLItems;

import java.util.ArrayList;
import java.util.List;

public class ItemSeal extends ItemMulti{

    public ItemSeal() {
        super("seal");
        PLItems.enchantingSealSandStorm = this.next();
        PLItems.enchantingSealAnotherWorld = this.next();
        PLItems.enchantingSealOasis = this.next();
        PLItems.enchantingSealSpring = this.next();

    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<>();
        list.add("enchanting_seal_sand_storm");
        list.add("enchanting_seal_another_world");
        list.add("enchanting_seal_oasis");
        list.add("enchanting_seal_spring");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "seal";
    }
}
