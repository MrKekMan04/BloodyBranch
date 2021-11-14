package com.mrkekman04.bloodybranch.core.helper;

import com.google.common.base.Function;
import com.sun.javafx.scene.traversal.Direction;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderHelpers {

    public static TextureAtlasSprite EMPTYICON;

    public static ResourceLocation TEXTURE_MAP = TextureMap.LOCATION_BLOCKS_TEXTURE;

    public static final Function<ResourceLocation, TextureAtlasSprite> TEXTURE_GETTER = new Function<ResourceLocation, TextureAtlasSprite>() {
        public TextureAtlasSprite apply(ResourceLocation location) {
            return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
        }
    };

    public static TextureAtlasSprite getBlockIcon(Block block) {
        return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(block.getDefaultState());
    }


    public static TextureAtlasSprite getFluidIcon(Fluid fluid, EnumFacing side) {
        return getFluidIcon(new FluidStack(fluid, 1000), side);
    }

    public static TextureAtlasSprite getFluidIcon(FluidStack fluid, EnumFacing side) {
        Block defaultBlock = Blocks.WATER;
        Block block = defaultBlock;
        if(fluid.getFluid().getBlock() != null) {
            block = fluid.getFluid().getBlock();
        }

        if(side == null) side = EnumFacing.UP;

        TextureAtlasSprite icon = TEXTURE_GETTER.apply(fluid.getFluid().getFlowing(fluid));
        if(icon == null || (side == EnumFacing.UP || side == EnumFacing.DOWN)) {
            icon = TEXTURE_GETTER.apply(fluid.getFluid().getStill(fluid));
        }
        if(icon == null) {
            icon = getBlockIcon(block);
            if(icon == null) {
                icon = getBlockIcon(defaultBlock);
            }
        }

        return icon;
    }

}
