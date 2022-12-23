package io.github.mortuusars.sootychimneys.integration.jei;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.resource.SootScrapingJeiRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JeiRecipeTypes {
    public static final RecipeType<SootScrapingJeiRecipe> SOOT_SCRAPING =
            RecipeType.create(SootyChimneys.MOD_ID, "soot_scraping", SootScrapingJeiRecipe.class);
}
