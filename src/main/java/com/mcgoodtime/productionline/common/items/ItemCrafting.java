package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLItems;

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
        PLItems.heatInsulationMaterial = this.next();
        PLItems.smallCompressedWaterHyacinth = this.next();
        PLItems.redstoneModule = this.next();
        PLItems.lazuliModule = this.next();
        PLItems.advSolarLensGroup = this.next();
        PLItems.advSolarLensCluster = this.next();
        PLItems.opticalGlass = this.next();
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<>();
        list.add("heat_insulation_material");
        list.add("small_compressed_water_hyacinth");
        list.add("redstone_module");
        list.add("lazuli_module");
        list.add("sawdust");
        list.add("adv_solar_lens_unit");
        list.add("adv_solar_lens_group");
        list.add("adv_solar_lens_cluster");
        list.add("optical_glass");
        list.add("rigid_paper");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "crafting";
    }
}

