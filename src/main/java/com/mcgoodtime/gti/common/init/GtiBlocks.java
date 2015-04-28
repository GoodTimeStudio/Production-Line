/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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

import com.mcgoodtime.gti.common.blocks.BlockWaterHyacinth;
import com.mcgoodtime.gti.common.blocks.OreIridium;
import com.mcgoodtime.gti.common.blocks.machines.GenGasKu;
import com.mcgoodtime.gti.common.core.GtiConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import org.apache.logging.log4j.Level;

/**
 * Gti blocks.
 *
 * @author liach
 */
public class GtiBlocks {

    public static Block waterHyacinth;
    public static BlockContainer genGasKU;
    public static Block oreIridium;

    public static void init() {
        waterHyacinth = new BlockWaterHyacinth();
        genGasKU = new GenGasKu();
        oreIridium = new OreIridium();
        GtiConfig.gtiLogger.log(Level.INFO, "waterhyacinth" + Integer.toString(Block.getIdFromBlock(waterHyacinth)));
        GtiConfig.gtiLogger.log(Level.INFO, "gengasku" + Integer.toString(Block.getIdFromBlock(genGasKU)));
        GtiConfig.gtiLogger.log(Level.INFO, "oreiridium" + Integer.toString(Block.getIdFromBlock(oreIridium)));
    }
}
