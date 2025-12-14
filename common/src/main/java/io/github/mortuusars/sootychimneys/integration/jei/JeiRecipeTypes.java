package io.github.mortuusars.sootychimneys.integration.jei;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import mezz.jei.api.recipe.types.IRecipeType;

public class JeiRecipeTypes {
    public static final IRecipeType<SootCoveringJeiRecipe> SOOT_COVERING =
          IRecipeType.create(SootyChimneys.resource("soot_covering"), SootCoveringJeiRecipe.class);
    public static final IRecipeType<SootScrapingRecipe> SOOT_SCRAPING =
          IRecipeType.create(SootyChimneys.resource("soot_scraping"), SootScrapingRecipe.class);
}
