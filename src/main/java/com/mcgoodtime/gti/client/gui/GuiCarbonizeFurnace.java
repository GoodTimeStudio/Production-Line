package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCarbonizeFurnace extends GuiContainer {
    public ContainerCarbonizeFurnace container;
    public String name;
    private static final ResourceLocation background = new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/client/gui/GuiCarbonizeFurnace.png");;

    public GuiCarbonizeFurnace(ContainerCarbonizeFurnace container1) {
        super(container1);
        this.container = container1;

        this.name = StatCollector.translateToLocal(GtiBlocks.carbonizeFurnace.getLocalizedName());
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);

        int i1;
        if(((TileCarbonizeFurnace)this.container.base).fuel > 0) {
            i1 = ((TileCarbonizeFurnace)this.container.base).gaugeFuelScaled(12);
            this.drawTexturedModalRect(j + 56, k + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = ((TileCarbonizeFurnace)this.container.base).gaugeProgressScaled(24);
        this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
    }
}