package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerEVSU;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.GuiIconButton;
import ic2.core.IC2;
import ic2.core.util.GuiTooltiphelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;

/**
 * Created by suhao on 2015-6-10-0010.
 */

@SideOnly(Side.CLIENT)
public class GuiEVSU extends GuiContainer {

    private final ContainerEVSU container;
    private final String armorInv;
    private final String level;
    private final String name;
    private static final ResourceLocation BACKGROUND = new ResourceLocation(RESOURCE_DOMAIN + ":" + "textures/client/gui/GuiEVSU.png");

    public GuiEVSU(ContainerEVSU container) {
        super(container);

        this.container = container;
        this.armorInv = StatCollector.translateToLocal("ic2.EUStorage.gui.info.armor");
        this.level = StatCollector.translateToLocal("ic2.EUStorage.gui.info.level");
        this.name = StatCollector.translateToLocal("tile.EVSU.name"); //Lazy on my part? Maybe. Works though!
    }

    @Override
    public void initGui(){
        super.initGui();
        this.buttonList.add(new GuiIconButton(0,
                (this.width - this.xSize) / 2 + 10,
                (this.height - this.ySize) / 2 + 7,
                20, 20, new ItemStack(Items.redstone),
                true));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 8, 16448255);
        //this.fontRendererObj.drawString(this.armorInv, 8, this.ySize - 126 + 3, ConfigHandler.AFSUTextColor);

        this.fontRendererObj.drawString(this.level, 16, 29, 16448255);
        int energy = new Double(Math.min((this.container.base).energy, (container.base).maxStorage)).intValue();
        this.fontRendererObj.drawString(" " + energy, 37, 39, 16448255);
        this.fontRendererObj.drawString("/" + (container.base).maxStorage, 37, 49, 16448255);

        String output = StatCollector.translateToLocalFormatted("ic2.EUStorage.gui.info.output", container.base.output);
        this.fontRendererObj.drawString(output, 26, 60, 16448255);

        GuiTooltiphelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, (container.base).getredstoneMode(),
                10, 7, 29, 26);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y){
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.BACKGROUND);
        int j = (this.width - this.xSize) / 2; //Good here
        int k = (this.height - this.ySize) / 2; //good here
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);//Renders the actual gui texture.
        if (container.base.energy > 0.0D) {
            int i1 = new Double(((176F * container.base.getChargeLevel()) / 1.6D) + 5.0D).intValue();//Calculates the current energy in afsu.
            drawTexturedModalRect(j + 8, k + 73,
                    0, 251, i1 + 1, 5);//Renders the blue energy bar.
        }
    }

    @Override
    protected void actionPerformed(GuiButton guibutton){
        super.actionPerformed(guibutton);
        if (guibutton.id == 0) IC2.network.get().initiateClientTileEntityEvent(container.base, 0);
    }
}
