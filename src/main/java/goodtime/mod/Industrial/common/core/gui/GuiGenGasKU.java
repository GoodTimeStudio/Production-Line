package goodtime.mod.Industrial.common.core.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goodtime.mod.Industrial.common.TileEntity.TileEntityGenGasKU;
import goodtime.mod.Industrial.common.inventory.ContainerGenGasKU;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiGenGasKU extends GuiContainer {

	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/KineticGenerator/GUIGasGenerator.png");
	private TileEntityGenGasKU tileGenGasKU;
	
	public GuiGenGasKU(InventoryPlayer invPlayer, TileEntityGenGasKU tile) {
		super(new ContainerGenGasKU(invPlayer, tile));
		this.tileGenGasKU = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int var1,int var2) {
		String string = this.tileGenGasKU.hasCustomInventoryName() ? this.tileGenGasKU.getInventoryName() : I18n.format(this.tileGenGasKU.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
