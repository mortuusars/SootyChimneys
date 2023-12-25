package io.github.mortuusars.sootychimneys.setup;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ToolActionIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final RegistryObject<RecipeSerializer<?>> SOOT_SCRAPING = Registry.RECIPE_SERIALIZERS
            .register("soot_scraping", SootScrapingRecipe.Serializer::new);

    public static void init() {
        CraftingHelper.register(SootyChimneys.resource("tool_action"), ToolActionIngredient.SERIALIZER);
    }
}
