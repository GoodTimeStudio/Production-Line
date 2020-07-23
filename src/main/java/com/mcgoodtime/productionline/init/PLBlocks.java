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
package com.mcgoodtime.productionline.init;

import com.mcgoodtime.productionline.blocks.*;
import com.mcgoodtime.productionline.core.ProductionLine;
//import com.mcgoodtime.productionline.blocks.generator.BlockAdvSolar;
//import com.mcgoodtime.productionline.blocks.generator.BlockFluidKineticGenerator;
//import com.mcgoodtime.productionline.items.ItemWaterHyacinth;
import com.mcgoodtime.productionline.tiles.subtile.functional.SubTileFlowerOfOsiris;
import lib.LibBlockNames;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;

/**
 * ProductionLine blocks.
 *
 * @author liach
 */
public class PLBlocks {
    public static BlockPL machine;

    public static BlockPL oreIridium;
    public static Block generator;
    public static Block flower;

    public static void init() {
        oreIridium = new BlockPL(Material.ROCK, "ore_iridium", 10, 20, "pickaxe", 3);
        generator = new BlockGenerator();
        flower = new FlowerPL();

        initTileEntities();
        //new BlockMisc();
        //machine = new BlockMachine();
    }

    private static void initTileEntities() {
        BotaniaAPI.registerSubTile(LibBlockNames.THE_FLOWER_OF_OSIRIS, SubTileFlowerOfOsiris.class);
        registerSubTileWithMini(LibBlockNames.THE_FLOWER_OF_OSIRIS, SubTileFlowerOfOsiris.class);
    }

    private static void registerSubTileWithMini(String key, Class<? extends SubTileEntity> clazz) {
        BotaniaAPI.registerSubTile(key, clazz);

        for(Class innerClazz : clazz.getDeclaredClasses())
            if(innerClazz.getSimpleName().equals("Mini"))
                BotaniaAPI.registerMiniSubTile(key + "Chibi", innerClazz, key);
    }


}
