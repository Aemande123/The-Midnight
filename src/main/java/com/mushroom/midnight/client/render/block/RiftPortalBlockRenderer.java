package com.mushroom.midnight.client.render.block;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mushroom.midnight.Midnight;
import com.mushroom.midnight.client.model.BlockEntityModel;
import com.mushroom.midnight.common.tile.RiftPortalTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RiftPortalBlockRenderer extends TileEntityRenderer<RiftPortalTileEntity> {
    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final ResourceLocation[] MASKS = new ResourceLocation[] {
            new ResourceLocation(Midnight.MODID, "textures/effects/rift_portal_mask_1.png"),
            new ResourceLocation(Midnight.MODID, "textures/effects/rift_portal_mask_2.png")
    };

    private static final BlockEntityModel BLOCK_MODEL = new BlockEntityModel();

    @Override
    public void render(RiftPortalTileEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        if (entity == null) return;

        double ticks = CLIENT.world.getGameTime() + partialTicks;
        float alphaLevel = (float) Math.sin(ticks * 0.08) / 2.0F + 0.5F;

        BlockPos pos = entity.getPos();
        long seed = MathHelper.getCoordinateRandom(pos.getX(), pos.getY(), pos.getZ());
        long textureSeed = seed ^ 8211203336981069197L;
        long rotationSeed = seed ^ 526247544445692899L;

        CLIENT.getTextureManager().bindTexture(MASKS[(int) (textureSeed & 1)]);

        GlStateManager.pushMatrix();
        GlStateManager.translated(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.rotatef((rotationSeed & 3) * 90.0F, 0.0F, 1.0F, 0.0F);

        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.alphaFunc(GL11.GL_GREATER, alphaLevel);
        GlStateManager.polygonOffset(-1.0F, -10.0F);
        GlStateManager.enablePolygonOffset();

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        BLOCK_MODEL.render();

        GlStateManager.polygonOffset(0.0F, 0.0F);
        GlStateManager.disablePolygonOffset();

        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);

        GlStateManager.popMatrix();
    }
}
