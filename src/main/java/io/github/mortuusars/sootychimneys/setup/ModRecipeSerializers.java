package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {

    public static final RegistryObject<RecipeSerializer<?>> SOOT_SCRAPING = Registry.RECIPE_SERIALIZERS.register("soot_scraping", SootScrapingRecipe.Serializer::new);
}
