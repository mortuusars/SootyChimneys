package io.github.mortuusars.sootychimneys;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collection;

public class PlatformSpecificClient {
    @ExpectPlatform
    public static Collection<RecipeHolder<SootScrapingRecipe>> getSootScrapingRecipes() {
        throw new AssertionError();
    }
}
