package com.mcgoodtime.productionline.common.blocks;

import com.google.common.collect.Maps;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

import java.util.Map;

/**
 * Created by BestOwl on 2017/3/5.
 *
 * Custom block state mapper for machine.
 * @author BestOwl
 */
public class MachineStateMapper extends StateMapperBase {

    @Override
    @SuppressWarnings("unchecked")
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Map map = Maps.newLinkedHashMap(state.getProperties());
        String s = String.format("%s:%s", Block.REGISTRY.getNameForObject(state.getBlock()).getResourceDomain(), PLBlocks.machine.internalName);


        Object obj = map.remove(BlockMachine.PROPERTY_ACTIVE);
        if (obj instanceof Boolean) {
            if ((Boolean) obj) {
                s += "_active";
            }
        }

        return new ModelResourceLocation(s, this.getPropertyString(map));
    }
}
