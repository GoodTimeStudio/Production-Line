package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCarbonizeFurnace extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(Gti.RESOURCE_DOMAIN,
            "textures/client/gui/GuiCarbonizeFurnace.png");
    private TileCarbonizeFurnace tileFurnace;

    public GuiCarbonizeFurnace(InventoryPlayer playerInventory, TileCarbonizeFurnace furnace) {
        super(new ContainerCarbonizeFurnace(playerInventory, furnace));
        this.tileFurnace = furnace;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = I18n.format(Gti.GUI_PREFIX + "CarbonizeFurnace");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.tileFurnace.isBurning()) {
            int i1 = this.tileFurnace.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = this.tileFurnace.getCookProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }
}