package io.github.mortuusars.sootychimneys.integration.jei.category;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;
import io.github.mortuusars.sootychimneys.core.smoke.SmokeProperties;
import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
import io.github.mortuusars.sootychimneys.integration.jei.drawable.ChimneySmokeAnimatedDrawable;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.integration.jei.renderer.ScalableItemStackRenderer;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SootCoveringRecipeCategory implements IRecipeCategory<SootCoveringJeiRecipe> {
    public static final int BG_WIDTH = 153;
    public static final int BG_HEIGHT = 65;

    public static final Map<Item, Integer> CHIMNEY_SMOKE_Y_ORIGIN = new HashMap<>() {{
        put(SootyChimneys.Items.BRICK_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.DIRTY_BRICK_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.COBBLESTONE_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.DIRTY_COBBLESTONE_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.STONE_BRICK_CHIMNEY.get(), 0);
        put(SootyChimneys.Items.DIRTY_STONE_BRICK_CHIMNEY.get(), 0);
        put(SootyChimneys.Items.MUD_BRICK_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.DIRTY_MUD_BRICK_CHIMNEY.get(), 3);
        put(SootyChimneys.Items.IRON_CHIMNEY.get(), 2);
        put(SootyChimneys.Items.DIRTY_IRON_CHIMNEY.get(), 2);
        put(SootyChimneys.Items.COPPER_CHIMNEY.get(), -1);
        put(SootyChimneys.Items.DIRTY_COPPER_CHIMNEY.get(), -1);
        put(SootyChimneys.Items.TERRACOTTA_CHIMNEY.get(), 10);
        put(SootyChimneys.Items.DIRTY_TERRACOTTA_CHIMNEY.get(), 10);
    }};

    private final Component title;
    private final IDrawable background;
    private final IDrawableStatic icon;
    private final IGuiHelper helper;

    public SootCoveringRecipeCategory(IGuiHelper helper) {
        this.helper = helper;
        title = Component.translatable("jei.sootychimneys.category.soot_covering");

        ResourceLocation texture = SootyChimneys.resource("textures/gui/jei/soot_covering.png");

        background = helper.createDrawable(texture, 0, 0, BG_WIDTH, BG_HEIGHT);
        icon = helper.drawableBuilder(SootyChimneys.resource("textures/gui/jei/soot_covering_icon.png"), 0, 0, 16, 16)
                .setTextureSize(16, 16)
                .build();
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull SootCoveringJeiRecipe recipe, @NotNull IFocusGroup focuses) {
        ChimneySmokeAnimatedDrawable ingredientSmoke = createSmokeDrawable(recipe.getCleanChimney());
        Integer yOffset = CHIMNEY_SMOKE_Y_ORIGIN.get(recipe.getCleanChimney().getItem());
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 18)
                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
                .setOverlay(ingredientSmoke, (int) ((16 * 2.5f) / 2 - 8), yOffset)
                .addItemStack(recipe.getCleanChimney())
                .setSlotName("CleanChimney");

        ChimneySmokeAnimatedDrawable resultSmoke = createSmokeDrawable(recipe.getDirtyChimney());
        Integer yOffset1 = CHIMNEY_SMOKE_Y_ORIGIN.get(recipe.getDirtyChimney().getItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 18)
                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
                .setOverlay(resultSmoke, (int) ((16 * 2.5f) / 2 - 8), yOffset1)
                .addItemStack(recipe.getDirtyChimney())
                .setSlotName("DirtyChimney");
    }

    private ChimneySmokeAnimatedDrawable createSmokeDrawable(ItemStack chimney) {
        ChimneySmokeAnimatedDrawable drawable = new ChimneySmokeAnimatedDrawable(helper);

        if (chimney.getItem() instanceof BlockItem blockItem
                && blockItem.getBlock() instanceof ChimneyBlock chimneyBlock) {
            SmokeProperties smokeProperties = chimneyBlock.getType().smokeProperties();
            drawable.setSpeed(smokeProperties.getSpeed());
            drawable.setIntensity(smokeProperties.getIntensity());
        }

        return drawable;
    }

    @Override
    public @NotNull Component getTitle() {
        return title;
    }

    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public @NotNull RecipeType<SootCoveringJeiRecipe> getRecipeType() {
        return JeiRecipeTypes.SOOT_COVERING;
    }
}
