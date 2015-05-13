package com.mcgoodtime.gti.common.gxy;


import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;


/**
 * Created by gxy on 2015/5/9.
 */
public class GXYBlock extends Block {

    private String name="gxyBlock";

    public GXYBlock()
    {
        super(Material.rock);
        this.setBlockName("gxyBlock");
        this.setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this,name);
    }
}
