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
package com.mcgoodtime.gti.common.tiles.tileslots;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.12.0012.
 *
 * @author BestOwl
 */
public class TileSlotUpgrade extends TileSlot {

    public TileSlotUpgrade(TileContainer tile, SlotMode mode) {
        super(tile, mode);
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @Override
    public boolean canInput(ItemStack itemStack) {
        if (itemStack.getItem() instanceof IUpgradeItem && this.tile instanceof IUpgradableBlock) {
            return ((IUpgradeItem) itemStack.getItem()).isSuitableFor(itemStack, ((IUpgradableBlock) this).getUpgradableProperties());
        }
        return false;
    }
}
