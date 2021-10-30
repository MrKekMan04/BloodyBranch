package com.mrkekman04.bloodybranch.gui;

import com.mrkekman04.bloodybranch.container.ContainerFactory;
import com.mrkekman04.bloodybranch.reference.Reference;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFactory extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/factory.png");

    private final EntityPlayer player;
    private final TileEntityFactory te;

    public GuiFactory(TileEntityFactory te, EntityPlayer player) {
        super(new ContainerFactory(player, te));
        this.te = te;
        this.player = player;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }


}
