package goodtime.mod.Industrial.client.gui;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GUIGenGasKU extends GuiScreen {

	private GuiScreen parScreen;
	
	public GUIGenGasKU(GuiScreen par) {
		parScreen = par;//记录哪个界面打开了它
		//在这里初始化与界面无关的数据
		
	}
	
	@Override
	public void initGui() {
		//每当界面打开时调用——部署控件
		super.initGui();
		Keyboard.enableRepeatEvents(true);
	}


	@Override
	public void drawScreen(int x, int y, float v) {
		//绘制文本等非控件内容，会被控件覆盖
		mc.renderEngine.bindTexture(new ResourceLocation("gti", "textures/gui/GUIGenGasKU.png"));
		drawTexturedModalRect(this.width / 2 - 175 / 2 , this.height / 2 - 175 / 2, 0, 0, 175, 175);//绘制材质
		drawCenteredString(fontRendererObj, "燃气动能发生机", 100, 20, 0xFFFFFF);//绘制标题
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
