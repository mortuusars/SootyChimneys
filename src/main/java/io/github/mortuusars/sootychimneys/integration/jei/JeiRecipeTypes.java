package io.github.mortuusars.sootychimneys.integration.jei;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootScrapingWithLootTablesJeiRecipe;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JeiRecipeTypes {
    public static final RecipeType<SootCoveringJeiRecipe> SOOT_COVERING =
            RecipeType.create(SootyChimneys.MOD_ID, "soot_covering", SootCoveringJeiRecipe.class);
    public static final RecipeType<SootScrapingRecipe> SOOT_SCRAPING =
            RecipeType.create(SootyChimneys.MOD_ID, "soot_scraping", SootScrapingRecipe.class);
    public static final RecipeType<SootScrapingWithLootTablesJeiRecipe> SOOT_SCRAPING_LOOT_TABLES =
            RecipeType.create(SootyChimneys.MOD_ID, "soot_scraping_loot_tables", SootScrapingWithLootTablesJeiRecipe.class);
}
