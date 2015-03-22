package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.TileEntity.TileEntityGenGasKU;
import com.mcgoodtime.gti.common.container.ContainerGenGasKU;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUIGenGasKU extends GuiContainer {

	private Container parScreen;
    private TileEntityGenGasKU tile;

	public GUIGenGasKU(ContainerGenGasKU container, TileEntityGenGasKU tileEntity) {
        super(container);
        this.tile = tileEntity;
        this.doesGuiPauseGame();
	}

	@Override
	public void initGui() {
		//每当界面打开时调用——部署控件
		super.initGui();
		Keyboard.enableRepeatEvents(true);
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        mc.renderEngine.bindTexture(new ResourceLocation("gti", "textures/gui/GUIGenGasKU.png"));
        drawTexturedModalRect(this.width / 2 - 175 / 2 , this.height / 2 - 175 / 2, 0, 0, 175, 175);//绘制材质
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
    }

    @Override
	public void drawScreen(int x, int y, float v) {
		//绘制文本等非控件内容，会被控件覆盖
	}

	//键盘按钮事件
	@Override
	protected void keyTyped(char p1, int p2) {
		super.keyTyped(p1, p2);
		if (p2 == Keyboard.KEY_E) {
			mc.displayGuiScreen(null);
		} 
	}

	//设置游戏是否暂停
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	//GUI关闭时调用
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

}
