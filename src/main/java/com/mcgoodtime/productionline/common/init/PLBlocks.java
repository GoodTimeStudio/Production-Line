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
package com.mcgoodtime.productionline.common.init;

import com.mcgoodtime.productionline.common.blocks.*;
import com.mcgoodtime.productionline.common.core.ProductionLine;
//import com.mcgoodtime.productionline.common.blocks.generator.BlockAdvSolar;
//import com.mcgoodtime.productionline.common.blocks.generator.BlockFluidKineticGenerator;
//import com.mcgoodtime.productionline.common.items.ItemWaterHyacinth;
import com.mcgoodtime.productionline.common.items.ItemBlockPL;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_ID;

/**
 * ProductionLine blocks.
 *
 * @author liach
 */
public class PLBlocks {
    public static ItemStack dehydratedWaterHyacinthblock;
    public static ItemStack compressedWaterHyacinth;

    public static BlockPL machine;
    public static ItemStack carbonizeFurnace;
    public static ItemStack heatDryer;
    public static ItemStack evsu;
    public static ItemStack cseu;
    public static ItemStack parallelSpaceSU;
    public static ItemStack advSolar;
    public static ItemStack fluidKineticGenerator;

    public static BlockPL oreIridium;
    public static BlockPL airBrakeCasing;
    public static BlockPL dryLog;
    public static BlockPL pad;
    
    public static BlockPL hardWood;
    public static BlockPL opticalGlass;
    
    public static Block waterHyacinth;

    public static void init() {
        pad = new BlockPL(Material.ROCK, "pad", 1, 0, null, 0);
        dryLog = new BlockPL(Material.ROCK, "dry_log", 3, 0, "axe", 0);
        oreIridium = new BlockPL(Material.ROCK, "ore_iridium", 10, 20, "pickaxe", 3);
        airBrakeCasing = new BlockOrientable(Material.IRON, "air_brake_casing");
        new BlockMisc();
        machine = new BlockMachine();

        // special registry TODO: Better registry system
        waterHyacinth = new BlockWaterHyacinth();
        waterHyacinth.setRegistryName(ProductionLine.loc("waterHyacinth"));
        ForgeRegistries.BLOCKS.register(waterHyacinth);
    }
}
