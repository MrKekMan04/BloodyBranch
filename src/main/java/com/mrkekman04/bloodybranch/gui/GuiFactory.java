package com.mrkekman04.bloodybranch.gui;

import com.mrkekman04.bloodybranch.container.ContainerFactory;
import com.mrkekman04.bloodybranch.core.helper.RenderHelpers;
import com.mrkekman04.bloodybranch.gui.component.DrawTooltip;
import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiFactory extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/factory.png");

    private final EntityPlayer player;
    private final TileEntityFactory te;

    private DrawTooltip.Square LPTankToolTip;
    private DrawTooltip.Square EnergyToolTip;

    public GuiFactory(TileEntityFactory te, EntityPlayer player) {
        super(new ContainerFactory(player, te));
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
            infoE.add(I18n.format("tip.factory.energy", te.getAmountEnergy(), te.getEnergyCapacity()));
            this.drawHoveringText(infoE, mouseX, mouseY);
        }

        if (LPTankToolTip.isBound(mouseX, mouseY)) {
            List<String> infoLP = new ArrayList<>();
            infoLP.add(I18n.format("tip.factory.LPTank", te.getAmountFluid(), te.getCapacityFluid()));
            this.drawHoveringText(infoLP, mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        // processScale
        int progressBar = this.getProcessScaled(22);
        this.drawTexturedModalRect(this.guiLeft + 95, this.guiTop + 36, 209, 0, progressBar, 16);

        if (te.getAmountEnergy() > 0) {
            int energyBar = getAmountEnergy(58);
            drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 14 + 58 - energyBar, 192, 58 - energyBar, 16, energyBar);
        }

        if (te.getAmountFluid() > 0) {
            int fluidBar = getAmountFluid(58);
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

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        fontRenderer.drawString(I18n.format(te.getInventoryName()), (xSize >> 1) - (fontRenderer.getStringWidth(I18n.format(te.getInventoryName())) >> 1), 6, 16777215, true);

        fontRenderer.drawString(I18n.format(player.inventory.getName()), xSize - fontRenderer.getStringWidth(I18n.format(player.inventory.getName())) - 8, ySize - 96 + 2, 16777215, true);
    }

    protected void drawTank(int xOffset, int yOffset, Fluid fluid, int level, int tankHeight) {
        if (fluid != null) {
            FluidStack stack = new FluidStack(fluid, 1);
            TextureAtlasSprite icon = RenderHelpers.getFluidIcon(stack, EnumFacing.UP);

            mc.renderEngine.bindTexture(RenderHelpers.TEXTURE_MAP);
            //drawTexturedModalRect(xOffset, yOffset - textureHeight - verticalOffset, icon, tankWidth, textureHeight);
        }
        this.mc.renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(xOffset, yOffset - tankHeight, 176, 58 - tankHeight, 16, tankHeight);
    }

    private int getAmountFluid(int pixels) {
        int i = this.te.getAmountFluid();
        int j = this.te.getCapacityFluid();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getAmountEnergy(int pixels) {
        int i = this.te.getAmountEnergy();
        int j = this.te.getEnergyCapacity();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getProcessScaled(int pixels) {
        int i = this.te.getProcessTime();
        int j = this.te.getWorkingTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

}
