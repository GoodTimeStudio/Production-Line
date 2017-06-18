/*
 *
 * This file is part of ProductionLine, licensed under MIT License (MIT).
 *
 * Copyright (c) 2017 GoodTime Studio <https://github.com/GoodTimeStudio>
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

package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.PLUtil;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.inventory.ContainerPackager;
import com.mcgoodtime.productionline.tiles.TilePackager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by BestOwl on 2017/3/26.
 *
 * @author BestOwl
 */
public class GuiPackager extends GuiPL<ContainerPackager> {

    int i = 0;

    public GuiPackager(ContainerPackager container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        TilePackager tile = this.container.tile;

        //energy bar
        int i1 = PLUtil.getGuiScaled(14, (float) tile.energy, tile.maxEnergy);
        this.drawTexturedModalRect(this.x + 56, this.y + 36 + 14 - i1, 176, 14 - i1, 14, i1 + 1);

        //progress bar
        i1 = PLUtil.getGuiScaled(24, tile.progress, (float) tile.requireEnergy);
        this.drawTexturedModalRect(this.x + 79, this.y + 40, 176, 15, i1 + 1, 4);

        if (tile.active) {
            this.drawTexturedModalRect(this.x + 85, this.y + 45 + 12 - (i / 10), 176, 20 + 12 - (i / 10), 12, i / 10);
            i++;

            if (i >= 120) {
                i = 0;
            }
        }
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/GuiPackager.png");
    }
}
