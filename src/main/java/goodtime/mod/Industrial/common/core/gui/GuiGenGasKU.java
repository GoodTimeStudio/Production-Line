package goodtime.mod.Industrial.common.core.gui;

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
	}

	@Override
	public void drawScreen(int x, int y, float v) {
		//绘制文本等非控件内容，会被控件覆盖
		mc.renderEngine.bindTexture(new ResourceLocation("gti", "textures/gui/GUIGenGasKU.png"));
		drawTexturedModalRect(50, 50, 0, 0, 165, 178);//绘制材质
		drawCenteredString(fontRendererObj, "燃气动能发生机", 100, 20, 0xFFFFFF);//绘制标题
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
	
}
