package com.mcgoodtime.productionline.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * Created by BestOwl on 2017/3/12.
 *
 * block and item that provide model.
 */
public interface IBlockModelProvider {

    ModelResourceLocation getModelResourceLocation(int meta);
}
