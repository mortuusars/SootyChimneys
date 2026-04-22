package io.github.mortuusars.sootychimneys.fabric;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Collection;
import java.util.List;

public class PlatformSpecificClientImpl {
    public static Collection<RecipeHolder<SootScrapingRecipe>> getSootScrapingRecipes() {
        if (Minecraft.getInstance().level instanceof ClientLevel level) {
            return level.recipeAccess().getSynchronizedRecipes().getAllOfType(SootyChimneys.RecipeTypes.SOOT_SCRAPING.get());
        }
        return List.of();
    }
}
