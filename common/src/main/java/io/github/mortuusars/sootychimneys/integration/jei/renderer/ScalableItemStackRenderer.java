package io.github.mortuusars.sootychimneys.integration.jei.renderer;

import com.mojang.logging.LogUtils;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

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
    public void render(@NonNull GuiGraphicsExtractor graphics, ItemStack ingredient) {
        if (ingredient != null) {
            graphics.pose().scale(xScale, yScale);
            graphics.item(ingredient, 0, 0);
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
    public @NotNull List<Component> getTooltip(ItemStack ingredient, @NonNull TooltipFlag tooltipFlag) {
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
