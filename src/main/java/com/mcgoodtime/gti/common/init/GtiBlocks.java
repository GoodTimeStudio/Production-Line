/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.blocks.*;
import com.mcgoodtime.gti.common.blocks.generator.BlockFluidKineticGenerator;
import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.items.ItemWaterHyacinth;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;

/**
 * Gti blocks.
 *
 * @author liach
 */
public class GtiBlocks {
    public static ItemStack dehydratedWaterHyacinthblock;
    public static ItemStack compressedWaterHyacinth;

    public static Block airBrakeCasing;
    public static Block waterHyacinth;
    public static Block fluidKineticGenerator;
    public static Block oreIridium;
    public static Block carbonizeFurnace;
    public static BlockContainer evsu;
    public static Block heatDryer;

    public static void init() {
        //compressedWaterHyacinth = new BlockGti(Material.rock, "CompressedWaterHyacinth", 0.5F, 0.3F, null, 0);
        //dehydratedWaterHyacinthblock=new BlockGti(Material.rock, "DehydratedWaterHyacinthBlock", 1.0F, 0.3F, null, 0);
        new BlockMisc();

        carbonizeFurnace = new BlockCarbonizeFurnace();
        airBrakeCasing = new BlockGti(Material.iron, "AirBrakeCasing");
        oreIridium = new BlockGti(Material.rock, "oreIridium", 10, 20, "pickaxe", 3);
        fluidKineticGenerator = new BlockFluidKineticGenerator();
        heatDryer = new BlockHeatDryer();

        // special registry TODO: Better registry system
        waterHyacinth = new BlockWaterHyacinth();
        evsu = new BlockEVSU();

        GameRegistry.registerBlock(waterHyacinth, ItemWaterHyacinth.class, "WaterHyacinth");
        GameRegistry.registerBlock(evsu, "EVSU");

        GtiConfig.gtiLogger.log(Level.INFO, "waterhyacinth" + Integer.toString(Block.getIdFromBlock(waterHyacinth)));
        GtiConfig.gtiLogger.log(Level.INFO, "FluidKineticGenerator" + Integer.toString(Block.getIdFromBlock(fluidKineticGenerator)));
        GtiConfig.gtiLogger.log(Level.INFO, "carbonizefurnace" + Integer.toString(Block.getIdFromBlock(carbonizeFurnace)));
        GtiConfig.gtiLogger.log(Level.INFO,"heatdryer" + Integer.toString(Block.getIdFromBlock(heatDryer)));
    }
}
