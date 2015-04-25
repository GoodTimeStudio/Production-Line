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
package com.mcgoodtime.gti.common.recipes;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.init.GtiItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Smelting {
	public static float xp = 1;
	public static void initSmelting() {
		GameRegistry.addSmelting(GtiBlocks.oreIridium, new ItemStack(GtiItems.ingotIridium), xp);
		GameRegistry.addSmelting(GtiItems.dustIridium, new ItemStack(GtiItems.ingotIridium), xp);
		GameRegistry.addSmelting(GtiItems.crushedIridium, new ItemStack(GtiItems.ingotIridium), xp);
		GameRegistry.addSmelting(GtiItems.cleanedCrushedIridium, new ItemStack(GtiItems.ingotIridium), xp);
	}
}
