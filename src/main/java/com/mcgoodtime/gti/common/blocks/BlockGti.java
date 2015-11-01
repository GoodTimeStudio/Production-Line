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
package com.mcgoodtime.gti.common.blocks;

import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;
import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;
import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;

import com.mcgoodtime.gti.common.core.GtiConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.apache.logging.log4j.Level;

/**
 * Created by suhao on 2015-6-10-0010.
 *
 * @author suhao
 */
public class BlockGti extends Block {
    public String blockName;

    public BlockGti(Material material, String name, float hardness, float resistance,
                       String harvestLevelToolClass, int harvestLevel) {
        this(material, name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvestLevelToolClass, harvestLevel);
    }

    public BlockGti(Material material, String name) {
        super(material);
        this.setBlockName(MOD_ID + "." + "block" + "." + name);
        this.setBlockTextureName(RESOURCE_DOMAIN + ":" + "block" + name);
        this.setCreativeTab(creativeTabGti);
        GameRegistry.registerBlock(this, name);
        GtiConfig.gtiLogger.log(Level.INFO, name + Integer.toString(Block.getIdFromBlock(this)));
        this.blockName = name;
    }
}
