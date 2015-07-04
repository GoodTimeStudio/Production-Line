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
package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerGenGasKu;
import com.mcgoodtime.gti.common.tiles.TileGenGasKu;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

/**
 * The Gui.
 *
 * @author liach
 */
@SideOnly(Side.CLIENT)
public class GuiGenGasKu extends GuiContainer {
    private static final ResourceLocation background = new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/client/gui/GUIGenGasKU.png");;

    private int ySize;
    private int xSize;
    private int x;
    private int y;

    private TileGenGasKu tile;

    public GuiGenGasKu(InventoryPlayer inventoryPlayer, TileGenGasKu tile) {
        super(new ContainerGenGasKu(inventoryPlayer, tile));
        this.tile = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    public void initGui() {
        super.initGui();
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {
        this.fontRendererObj.drawString(
                I18n.format(
                        "container.inventory",
                        new Object[0]
                ),
                8,
                this.ySize - 96 + 2,
                4210752
        );
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        this.mc.getTextureManager().bindTexture(background);
        this.mc.fontRenderer.drawString(
                StatCollector.translateToLocal(
                        Gti.GUI_PREFIX + "GenGasKu"
                ),
                100,
                10,
                16777215
        );
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        drawTexturedModalRect(this.x, this.y, 0, 0, this.xSize, this.ySize);
    }

    protected void keyTyped(char c, int i) {
        super.keyTyped(c, i);
        if (i == Keyboard.KEY_E) {
            this.mc.displayGuiScreen(null);
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }
}
