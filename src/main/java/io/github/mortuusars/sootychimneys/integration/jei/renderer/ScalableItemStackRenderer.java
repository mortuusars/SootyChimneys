package io.github.mortuusars.sootychimneys.integration.jei.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ScalableItemStackRenderer implements IIngredientRenderer<ItemStack> {
    private float xScale;
    private float yScale;
    private float zScale;

    public ScalableItemStackRenderer(float xScale, float yScale, float zScale) {
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }

    public ScalableItemStackRenderer(float scale) {
        this.xScale = scale;
        this.yScale = scale;
        this.zScale = scale;
    }

    @Override
    public void render(PoseStack stack, ItemStack ingredient) {
        if (ingredient != null) {
            stack.pushPose();
            {
                stack.scale(xScale, yScale, zScale);
                drawItemStack(ingredient, stack, 0, 0, 16, 16, 16);
            }
            stack.popPose();
        }
    }

    @Override
    public int getWidth() {
        return ((int) (xScale * 16));
    }

    @Override
    public int getHeight() {
        return ((int) (yScale * 16));
    }

    private static void drawItemStack(ItemStack itemStack, PoseStack poseStack, int xOffset, int yOffset, float xScale, float yScale, float zScale) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        BakedModel bakedModel = itemRenderer.getModel(itemStack, null, null, 0);

        Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        PoseStack stack = RenderSystem.getModelViewStack();
        stack.pushPose();
        stack.mulPoseMatrix(poseStack.last().pose());
        stack.translate(xOffset, yOffset, 100.0F + itemRenderer.blitOffset);
        stack.translate(8.0D, 8.0D, 0.0D);
        stack.scale(1.0F, -1.0F, 1.0F);
        stack.scale(xScale, yScale, zScale);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        boolean flag = !bakedModel.usesBlockLight();
        if (flag) {
            Lighting.setupForFlatItems();
        }

        itemRenderer.render(itemStack, ItemTransforms.TransformType.GUI, false, posestack1, multibuffersource$buffersource, 15728880, OverlayTexture.NO_OVERLAY, bakedModel);
        multibuffersource$buffersource.endBatch();
        RenderSystem.enableDepthTest();
        if (flag) {
            Lighting.setupFor3DItems();
        }

        stack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    @Override
    public List<Component> getTooltip(ItemStack ingredient, TooltipFlag tooltipFlag) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        try {
            return ingredient.getTooltipLines(player, tooltipFlag);
        } catch (RuntimeException | LinkageError e) {
            LogUtils.getLogger().error("Failed to get tooltip: {}", ingredient, e);
            return Collections.emptyList();
        }
    }
}
