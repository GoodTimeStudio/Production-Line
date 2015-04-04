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

import com.mcgoodtime.gti.common.tiles.TileGenGasKu;
import com.mcgoodtime.gti.common.inventory.ContainerGenGasKU;
import com.mcgoodtime.gti.common.core.Gti;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUIGenGasKU extends GuiContainer {

    private ResourceLocation background = new ResourceLocation(Gti.MODID.toLowerCase() + ":" + "textures/client/gui/GUIGenGasKU.png");

    private int xSize, ySize;
    private int x, y;
    private TileGenGasKu tile;

	public GUIGenGasKU(InventoryPlayer inventoryPlayer, TileGenGasKu tileEntity) {
        super(new ContainerGenGasKU(inventoryPlayer, tileEntity));
        this.tile = tileEntity;
        xSize = 176;
        ySize = 166;
	}

	@Override
	public void initGui() {
		//每当界面打开时调用——部署控件
		super.initGui();
		Keyboard.enableRepeatEvents(true);
	}

    //绘制文字，控件等
    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        //String s = this.tile.getInventoryName();
        //this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    //绘制背景
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        //绘制文本等非控件内容，会被控件覆盖
        this.mc.getTextureManager().bindTexture(background);
        this.x = (this.width - xSize) / 2;
        this.y = (this.height - ySize) / 2;
        drawTexturedModalRect(this.x ,this.y, 0, 0, xSize, ySize);//绘制材质
    }

    //键盘按钮事件
	@Override
	protected void keyTyped(char p1, int p2) {
		super.keyTyped(p1, p2);
		if (p2 == Keyboard.KEY_E) {
			mc.displayGuiScreen(null);
		} 
	}

	
	//GUI关闭时调用
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

}
