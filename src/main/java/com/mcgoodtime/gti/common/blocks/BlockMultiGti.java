package com.mcgoodtime.gti.common.blocks;

import net.minecraft.block.material.Material;

/*
 * Created by suhao on 2015.7.13.
 */
public class BlockMultiGti extends BlockGti {

    public BlockMultiGti(Material material, String name, float hardness, float resistance, String harvestLevelToolClass, int harvestLevel) {
        super(material, name, hardness, resistance, harvestLevelToolClass, harvestLevel);
    }

    public BlockMultiGti(Material material, String name) {
        super(material, name);
    }


}
