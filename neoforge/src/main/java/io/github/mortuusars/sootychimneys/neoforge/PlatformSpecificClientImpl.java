package io.github.mortuusars.sootychimneys.neoforge;

import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collection;
import java.util.List;

public class PlatformSpecificClientImpl {
    public static Collection<RecipeHolder<SootScrapingRecipe>> sootScrapingRecipes = List.of();

    public static Collection<RecipeHolder<SootScrapingRecipe>> getSootScrapingRecipes() {
        return sootScrapingRecipes;
    }
}
