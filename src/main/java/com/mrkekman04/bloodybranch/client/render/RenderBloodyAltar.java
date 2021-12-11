package com.mrkekman04.bloodybranch.client.render;

import com.mrkekman04.bloodybranch.tile.TileBloodyAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderBloodyAltar extends TileEntitySpecialRenderer<TileBloodyAltar> {

    @Override
    public void render(TileBloodyAltar te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 0.35, z + 0.5);
        GlStateManager.scale(0.7, 0.7, 0.7);
        GlStateManager.rotate(90, 1, 0, 0);
        mc.getRenderItem().renderItem(te.getItemOrb(), ItemCameraTransforms.TransformType.FIXED);
        GlStateManager.popMatrix();
    }
}
