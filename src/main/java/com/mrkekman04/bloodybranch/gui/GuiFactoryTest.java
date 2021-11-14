package com.mrkekman04.bloodybranch.gui;

import com.mrkekman04.bloodybranch.container.ContainerFactoryTest;
import com.mrkekman04.bloodybranch.core.helper.RenderHelpers;
import com.mrkekman04.bloodybranch.gui.component.DrawTooltip;
import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.tile.TileFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiFactoryTest extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/factory.png");

    private final EntityPlayer player;
    private final TileFactory te;

    private DrawTooltip.Square LPTankToolTip;
    private DrawTooltip.Square EnergyToolTip;

    public GuiFactoryTest(TileFactory te, EntityPlayer player) {
        super(new ContainerFactoryTest(player, te));
        this.te = te;
        this.player = player;


    }

    @Override
    public void initGui() {
        super.initGui();
        LPTankToolTip = new DrawTooltip.Square(guiLeft + 26, guiTop + 14, 58, 16);
        EnergyToolTip = new DrawTooltip.Square(guiLeft + 8, guiTop + 14, 58, 16);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        if (EnergyToolTip.isBound(mouseX, mouseY)) {
            List<String> infoE = new ArrayList<>();
            infoE.add(I18n.format("tip.factory.energy", te.getEnergyAmount(), te.getEnergyCapacity()));
            this.drawHoveringText(infoE, mouseX, mouseY);
        }

        if (LPTankToolTip.isBound(mouseX, mouseY)) {
            List<String> infoLP = new ArrayList<>();
            infoLP.add(I18n.format("tip.factory.LPTank", te.getFluidAmount(), te.getFluidCapacity()));
            this.drawHoveringText(infoLP, mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        fontRenderer.drawString(I18n.format(te.getInventoryName()), (xSize >> 1) - (fontRenderer.getStringWidth(I18n.format(te.getInventoryName())) >> 1), 6, 16777215, true);
        fontRenderer.drawString(I18n.format(player.inventory.getName()), xSize - fontRenderer.getStringWidth(I18n.format(player.inventory.getName())) - 8, ySize - 96 + 2, 16777215, true);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);


        if (te.getProcessTime() > 0) {
            int progressBar = this.getProcessScaled(22);
            this.drawTexturedModalRect(this.guiLeft + 95, this.guiTop + 36, 209, 0, progressBar, 16);
        }

        if (te.getEnergyAmount() > 0) {
            int energyBar = getEnergyScaled(58);
            drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 14 + 58 - energyBar, 192, 58 - energyBar, 16, energyBar);
        }

        if (te.getFluidAmount() > 0) {
            int fluidBar = getFluidScaled(58);
            drawTexturedModalRect(this.guiLeft + 26, this.guiTop + 14 + 58 - fluidBar, 176, 58 - fluidBar, 16, fluidBar);
            FluidStack fluid = te.getTank().getFluid();
            if (fluid != null) {
                FluidStack stack = new FluidStack(fluid, 1);
                TextureAtlasSprite icon = RenderHelpers.getFluidIcon(stack, EnumFacing.UP);

                mc.renderEngine.bindTexture(RenderHelpers.TEXTURE_MAP);
                drawTexturedModalRect(this.guiLeft + 26, this.guiTop + 14 + 58 - fluidBar, icon, 16, fluidBar);
            }
            this.mc.renderEngine.bindTexture(texture);
            this.drawTexturedModalRect(this.guiLeft + 26, this.guiTop + 14 + 58 - fluidBar, 176, 58 - fluidBar, 16, fluidBar);
        }
    }

    private int getFluidScaled(int pixels) {
        int i = this.te.getField(3);
        int j = this.te.getFluidCapacity();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getEnergyScaled(int pixels) {
        int i = this.te.getField(2);
        int j = this.te.getEnergyCapacity();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getProcessScaled(int pixels) {
        int i = this.te.getField(1);
        int j = this.te.getField(0);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}

