package io.github.mortuusars.sootychimneys.integration.jei.category;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
import io.github.mortuusars.sootychimneys.integration.jei.renderer.ScalableItemStackRenderer;
import io.github.mortuusars.sootychimneys.integration.jei.resource.SootScrapingJeiRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("removal")
public class SootScrapingRecipeCategory implements IRecipeCategory<SootScrapingJeiRecipe> {
    public static final ResourceLocation UID = SootyChimneys.resource("soot_scraping");
    public static final ResourceLocation TEXTURE = SootyChimneys.resource("textures/gui/jei/gui.png");

    public static final int BG_WIDTH = 152;
    public static final int BG_HEIGHT = 65;

    private final List<Component> SOOT_ITEMS_INFO = ImmutableList.of(
            new TranslatableComponent("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping.soot_items_info"));

    private final Component title;
    private final IDrawable background;
    private final IDrawableStatic icon;
    private final IDrawableStatic slot;
    private final IDrawableStatic shadow;
    private final IDrawableStatic toolArrow;
    private final IDrawableStatic progressArrow;
    private final IDrawableStatic byproductInfo;

    private float chimneyScale = 2.5f;
    private int chimneySize = (int)(16 * chimneyScale);
    private int ingredientChimneyPosX = 6;
    private int resultChimneyPosX = BG_WIDTH - chimneySize - ingredientChimneyPosX;
    private int chimneyPosY = BG_HEIGHT - chimneySize - 6;

    private int toolSlotX = ingredientChimneyPosX + chimneySize;
    private int toolSlotY = chimneyPosY - 16;

    public SootScrapingRecipeCategory(IGuiHelper helper) {
        title = new TranslatableComponent("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping");

        background = helper.createBlankDrawable(BG_WIDTH, BG_HEIGHT);
        icon = helper.createDrawable(TEXTURE, 1, 1, 16, 16);

        slot = helper.createDrawable(TEXTURE, 1, 20 , 16, 16);
        shadow  = helper.createDrawable(TEXTURE, 1, 39, 44, 27);
        toolArrow = helper.createDrawable(TEXTURE, 1, 69, 16, 12);
        progressArrow = helper.createDrawable(TEXTURE, 1, 84, 40, 13);
        byproductInfo = helper.createDrawable(TEXTURE, 1, 116, 14, 25);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SootScrapingJeiRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, ingredientChimneyPosX, chimneyPosY)
                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(chimneyScale))
                .addItemStack(recipe.getIngredientChimney())
                .setSlotName("DirtyChimney");

        builder.addSlot(RecipeIngredientRole.CATALYST, toolSlotX, toolSlotY)
                        .addIngredients(VanillaTypes.ITEM_STACK, recipe.getTools());

        builder.addSlot(RecipeIngredientRole.OUTPUT, resultChimneyPosX, chimneyPosY)
                .setCustomRenderer(VanillaTypes.ITEM_STACK, new ScalableItemStackRenderer(chimneyScale))
                .addItemStack(recipe.getResultChimney())
                .setSlotName("CleanChimney");
    }

    @Override
    public void draw(SootScrapingJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {

        int shadowYPos = chimneyPosY + chimneySize / 2 - 4;
        shadow.draw(stack, ingredientChimneyPosX + chimneySize / 2 - shadow.getWidth() / 2, shadowYPos);
        shadow.draw(stack, resultChimneyPosX + chimneySize / 2 - shadow.getWidth() / 2, shadowYPos);

        slot.draw(stack, toolSlotX, toolSlotY);
        toolArrow.draw(stack, toolSlotX - 8 - toolArrow.getWidth(), toolSlotY + 8 - toolArrow.getHeight() / 2);
        progressArrow.draw(stack, BG_WIDTH / 2 - progressArrow.getWidth() / 2, BG_HEIGHT / 2 - progressArrow.getHeight() / 2);

        if (CommonConfig.DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO.get()) {
            byproductInfo.draw(stack, BG_WIDTH / 2 - byproductInfo.getWidth() / 2, BG_HEIGHT - byproductInfo.getHeight() - 4);
        }
    }

    @Override
    public List<Component> getTooltipStrings(SootScrapingJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (CommonConfig.DISPLAY_JEI_SCRAPING_BYPRODUCTS_INFO.get()) {
            if ((mouseX >= BG_WIDTH / 2 - byproductInfo.getWidth() / 2 && mouseX < BG_WIDTH / 2 + byproductInfo.getWidth() / 2)
                    && (mouseY >= BG_HEIGHT - byproductInfo.getHeight() && mouseY < BG_HEIGHT))
                return SOOT_ITEMS_INFO;
        }

        return Collections.emptyList();
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SootScrapingJeiRecipe> getRecipeClass() {
        return this.getRecipeType().getRecipeClass();
    }

    @Override
    public RecipeType<SootScrapingJeiRecipe> getRecipeType() {
        return JeiRecipeTypes.SOOT_SCRAPING;
    }
}
