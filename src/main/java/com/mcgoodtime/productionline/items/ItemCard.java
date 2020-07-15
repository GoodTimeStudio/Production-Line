package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLItems;

import java.util.ArrayList;
import java.util.List;

public class ItemCard extends ItemMulti{

    public ItemCard() {
        super("card");
        PLItems.upgradleCardSandStorm = this.next();
        PLItems.upgradleCardAnotherWorld = this.next();
        PLItems.upgradleCardOasis = this.next();
        PLItems.upgradleCardSpring = this.next();
        PLItems.converterCardBlackCat = this.next();
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<>();
        list.add("upgradle_card_sand_storm");
        list.add("upgradle_card_another_world");
        list.add("upgradle_card_oasis");
        list.add("upgradle_card_spring");
        list.add("converter_card_black_cat");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "card";
    }
}
