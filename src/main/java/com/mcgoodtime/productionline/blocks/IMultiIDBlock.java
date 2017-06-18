package com.mcgoodtime.productionline.blocks;

import net.minecraft.block.properties.PropertyEnum;

import javax.annotation.Nonnull;

/**
 * Created by BestOwl on 2017/2/9.
 *
 * sub type block
 * @author BestOwl
 */
public interface IMultiIDBlock<T extends PropertyEnum> {

    @Nonnull
    T getBlockTypeContainer();

}
