package io.github.mortuusars.sootychimneys.integration.jei.category;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.JeiRecipeTypes;
import io.github.mortuusars.sootychimneys.integration.jei.resource.SootScrapingJeiRecipe;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class SootScrapingRecipeCategory implements IRecipeCategory<SootScrapingJeiRecipe> {
    public static final ResourceLocation UID = SootyChimneys.resource("soot_scraping");

    private final Component title;
    private final IDrawable background;
//    private final IDrawable slotIcon;
    private final IDrawable icon;

    public SootScrapingRecipeCategory(IGuiHelper helper) {
        title = new TranslatableComponent("jei." + SootyChimneys.MOD_ID + ".category.soot_scraping");
        background = helper.createDrawable(SootyChimneys.resource("textures/gui/jei/soot_scraping.png"), 0, 0, 118, 80);
        icon = helper.createDrawable(SootyChimneys.resource("textures/gui/jei/soot_scraping_icon.png"), 0, 0, 16, 16);
//        slotIcon = helper.createDrawable(backgroundImage, 119, 0, slotSize, slotSize);
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
