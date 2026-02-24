package io.github.mortuusars.sootychimneys.integration.jei.renderer;

import com.mojang.logging.LogUtils;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ScalableItemStackRenderer implements IIngredientRenderer<ItemStack> {
    private final float xScale;
    private final float yScale;
    private final float zScale;

    public ScalableItemStackRenderer(float scale) {
        this.xScale = scale;
        this.yScale = scale;
        this.zScale = scale;
    }

    @Override
    public void render(GuiGraphics guiGraphics, ItemStack ingredient) {
        if (ingredient != null) {
            guiGraphics.pose().pushPose();
            {
                guiGraphics.pose().scale(xScale, yScale, zScale);
                drawItemStack(guiGraphics, ingredient, 0, 0, 16, 16, 16);
            }
            guiGraphics.pose().popPose();
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

    private static void drawItemStack(GuiGraphics guiGraphics, ItemStack itemStack, int xOffset, int yOffset, float xScale, float yScale, float zScale) {
//        guiGraphics.pose().pushPose();
//        guiGraphics.pose().scale(xScale, yScale, zScale);

        guiGraphics.renderItem(itemStack, xOffset, yOffset);

//        guiGraphics.pose().popPose();


//        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//
//        BakedModel bakedModel = itemRenderer.getModel(itemStack, null, null, 0);
//
//        guiGraphics.renderItem();
//
//        Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS).setFilter(false, false);
//        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
//        RenderSystem.enableBlend();
//        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        PoseStack stack = RenderSystem.getModelViewStack();
//        stack.pushPose();
//        stack.mulPoseMatrix(poseStack.last().pose());
//        stack.translate(xOffset, yOffset, 100.0F + itemRenderer.render(););
//        stack.translate(8.0D, 8.0D, 0.0D);
//        stack.scale(1.0F, -1.0F, 1.0F);
//        stack.scale(xScale, yScale, zScale);
//        RenderSystem.applyModelViewMatrix();
//        PoseStack posestack1 = new PoseStack();
//        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
//        boolean flag = !bakedModel.usesBlockLight();
//        if (flag) {
//            Lighting.setupForFlatItems();
//        }
//
//        itemRenderer.render(itemStack, ItemTransforms.TransformType.GUI, false, posestack1, multibuffersource$buffersource, 15728880, OverlayTexture.NO_OVERLAY, bakedModel);
//        multibuffersource$buffersource.endBatch();
//        RenderSystem.enableDepthTest();
//        if (flag) {
//            Lighting.setupFor3DItems();
//        }
//
//        stack.popPose();
//        RenderSystem.applyModelViewMatrix();
    }

    @SuppressWarnings("removal")
    @Override
    public @NotNull List<Component> getTooltip(ItemStack ingredient, TooltipFlag tooltipFlag) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        try {
            return ingredient.getTooltipLines(player, tooltipFlag);
        } catch (Exception e) {
            LogUtils.getLogger().error("Failed to get tooltip: {}", ingredient, e);
            return Collections.emptyList();
        }
    }
}
