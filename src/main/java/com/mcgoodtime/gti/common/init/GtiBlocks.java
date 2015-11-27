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
import com.mcgoodtime.gti.common.items.ItemWaterHyacinth;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

/**
 * Gti blocks.
 *
 * @author liach
 */
public class GtiBlocks {
    public static ItemStack dehydratedWaterHyacinthblock;
    public static ItemStack compressedWaterHyacinth;
    public static ItemStack carbonizeFurnace;
    public static ItemStack heatDryer;
    public static ItemStack evsu;

    public static BlockGti fluidKineticGenerator;
    public static BlockGti oreIridium;
    public static BlockGti airBrakeCasing;
    public static BlockGti dryLog;

    public static Block waterHyacinth;

    public static void init() {
        dryLog = new BlockGti(Material.rock, "dryLog",1.5f , 0, "axe", 0);
        oreIridium = new BlockGti(Material.rock, "oreIridium", 10, 20, "pickaxe", 3);
        fluidKineticGenerator = new BlockFluidKineticGenerator();
        airBrakeCasing = new BlockMultiTexture(Material.iron, "AirBrakeCasing");
        new BlockMisc();
        new BlockMachine();
        new BlockEUStorage();

        // special registry TODO: Better registry system
        waterHyacinth = new BlockWaterHyacinth();

        //----------------------------
        //------Block Registry--------
        registerBlock(oreIridium);
        registerBlock(fluidKineticGenerator);
        registerBlock(airBrakeCasing);
        registerBlock(dryLog);

        GameRegistry.registerBlock(waterHyacinth, ItemWaterHyacinth.class, "WaterHyacinth");
    }

    private static void registerBlock(BlockGti blockGti) {
        GameRegistry.registerBlock(blockGti, blockGti.internalName);
    }
}
