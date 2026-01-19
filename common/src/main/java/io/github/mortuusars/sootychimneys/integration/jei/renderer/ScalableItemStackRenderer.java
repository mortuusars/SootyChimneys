package io.github.mortuusars.sootychimneys.integration.jei.renderer;

import com.mojang.logging.LogUtils;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
//            guiGraphics.pose().pushPose();
            {
                guiGraphics.pose().scale(xScale, yScale);
                guiGraphics.renderItem(ingredient, 0, 0);
            }
//            guiGraphics.pose().popPose();
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

    @Override
    public @NotNull List<Component> getTooltip(ItemStack ingredient, TooltipFlag tooltipFlag) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        try {
            return ingredient.getTooltipLines(Item.TooltipContext.of(Objects.requireNonNull(player).level()), player, tooltipFlag);
        } catch (Exception e) {
            LogUtils.getLogger().error("Failed to get tooltip: {}", ingredient, e);
            return Collections.emptyList();
        }
    }
}
