package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final RegistryObject<RecipeType<SootScrapingRecipe>> SOOT_SCRAPING = Registry.RECIPE_TYPES.register("soot_scraping",
            () -> new RecipeType<>() {
                public String toString() {
                    return SootyChimneys.MOD_ID + ":chopping";
                }
            });

    public static void init() {
    }
}
