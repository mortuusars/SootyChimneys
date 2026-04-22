package io.github.mortuusars.sootychimneys.integration.jei.category;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
import io.github.mortuusars.sootychimneys.integration.jei.SootyChimneysJeiPlugin;
import io.github.mortuusars.sootychimneys.integration.jei.renderer.ScalableItemStackRenderer;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.result.ChanceResult;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SootScrapingRecipeCategory implements IRecipeCategory<SootScrapingRecipe> {
    public static final int BG_WIDTH = 153;
    public static final int BG_HEIGHT = 65;

    private final Component title;
    private final IDrawableStatic icon;
    private final IDrawableStatic background;
    private final IDrawableStatic dust;
    private final IDrawableStatic slot;
    private final IDrawableStatic chanceSlot;

    public SootScrapingRecipeCategory(IGuiHelper helper) {
        title = Component.translatable("jei.sootychimneys.category.soot_scraping");

        Identifier texture = SootyChimneys.identifier("textures/gui/jei/soot_scraping.png");

        icon = helper.drawableBuilder(SootyChimneys.identifier("textures/gui/jei/soot_scraping_icon.png"), 0, 0, 16, 16)
              .setTextureSize(16, 16)
              .build();

        background = helper.createDrawable(texture, 0, 0, BG_WIDTH, BG_HEIGHT);

        dust = helper.createDrawable(texture, 155, 0, 10, 12);
        slot = helper.createDrawable(texture, 0, 65, 18, 18);
        chanceSlot = helper.createDrawable(texture, 18, 65, 18, 18);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SootScrapingRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 18)
              .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
              .add(recipe.chimney())
              .setSlotName("DirtyChimney");

        builder.addSlot(RecipeIngredientRole.CRAFTING_STATION, 44, 1)
              .addItemStacks(SootyChimneysJeiPlugin.getScrapingTools())
              .setSlotName("Tool");

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 18)
              .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(2.5f))
              .add(recipe.getResultChimney())
              .setSlotName("CleanChimney");

        List<ChanceResult> results = recipe.results()
              .stream()
              .filter(result -> result.chance() > 0)
              .toList();

        int slotX = 51 + (27 - ((results.size() * 18) / 2));

        for (int i = 0; i < Math.min(results.size(), SootScrapingRecipe.MAX_RESULTS); i++) {
            ChanceResult result = results.get(i);
            builder.addSlot(RecipeIngredientRole.OUTPUT, slotX + (18 * i) - 1, 47)
                  .setSlotName("Result" + i + 1)
                  .add(result.stack().create())
                  .addRichTooltipCallback((_, tooltip) -> {
                      if (result.chance() < 1.0f) {
                          float chance = result.chance() * 100;
                          String chanceString = chance < 1 ? "<1" : Integer.toString((int) chance);
                          tooltip.add(Component.translatable("jei.sootychimneys.chance", chanceString).withStyle(ChatFormatting.GOLD));
                      }
                  });
        }
    }

    @Override
    public void draw(SootScrapingRecipe recipe, @NonNull IRecipeSlotsView recipeSlotsView, @NonNull GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
        background.draw(graphics);

        List<ChanceResult> results = recipe.results()
              .stream()
              .filter(result -> result.chance() > 0)
              .toList();

        if (results.isEmpty())
            return;

        dust.draw(graphics, 72, 34);

        int slotX = 49 + (27 - ((results.size() * 18) / 2));

        for (int i = 0; i < results.size(); i++) {
            ChanceResult result = results.get(i);
            if (result.chance() >= 1.0f)
                slot.draw(graphics, slotX + (18 * i), 46);
            else
                chanceSlot.draw(graphics, slotX + (18 * i), 46);
        }
    }

    @Override
    public @NotNull Component getTitle() {
        return title;
    }

    @Override
    public int getWidth() {
        return BG_WIDTH;
    }

    @Override
    public int getHeight() {
        return BG_HEIGHT;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public @NotNull IRecipeType<SootScrapingRecipe> getRecipeType() {
        return JeiRecipeTypes.SOOT_SCRAPING;
    }
}
