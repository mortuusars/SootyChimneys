package io.github.mortuusars.sootychimneys.integration.jei;

//import com.google.common.collect.ImmutableList;
//import io.github.mortuusars.sootychimneys.SootyChimneys;
//import io.github.mortuusars.sootychimneys.config.CommonConfig;
//import io.github.mortuusars.sootychimneys.integration.jei.category.SootCoveringRecipeCategory;
//import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingRecipeCategory;
//import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
//import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootScrapingJeiRecipe;
//import io.github.mortuusars.sootychimneys.setup.ModItems;
//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.registration.IRecipeCategoryRegistration;
//import mezz.jei.api.registration.IRecipeRegistration;
//import net.minecraft.resources.ResourceLocation;

import com.google.common.collect.ImmutableList;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.config.Config;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootCoveringRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingWithLootTablesRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootScrapingWithLootTablesJeiRecipe;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import io.github.mortuusars.sootychimneys.setup.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JeiPlugin
public class SootyChimneysJeiPlugin implements IModPlugin {

    private static final ResourceLocation UID = SootyChimneys.resource("jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        if (Config.ADD_SOOT_COVERING_TO_JEI.get() && Config.DIRTY_CHANCE.get() > 0.0D)
            registration.addRecipeCategories(new SootCoveringRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        if (Config.ADD_SOOT_SCRAPING_TO_JEI.get()) {
            if (Config.USE_LOOT_TABLES_FOR_SCRAPING.get())
                registration.addRecipeCategories(new SootScrapingWithLootTablesRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
            else
                registration.addRecipeCategories(new SootScrapingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        }
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        registration.addRecipes(JeiRecipeTypes.SOOT_COVERING, ImmutableList.of(
                new SootCoveringJeiRecipe(ModItems.BRICK_CHIMNEY.get(), ModItems.DIRTY_BRICK_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.STONE_BRICK_CHIMNEY.get(), ModItems.DIRTY_STONE_BRICK_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.COPPER_CHIMNEY.get(), ModItems.DIRTY_COPPER_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.TERRACOTTA_CHIMNEY.get(), ModItems.DIRTY_TERRACOTTA_CHIMNEY.get())
        ));

        if (Config.USE_LOOT_TABLES_FOR_SCRAPING.get()) {
            registration.addRecipes(JeiRecipeTypes.SOOT_SCRAPING_LOOT_TABLES, ImmutableList.of(
                    new SootScrapingWithLootTablesJeiRecipe(ModItems.DIRTY_BRICK_CHIMNEY.get(), ModItems.BRICK_CHIMNEY.get()),
                    new SootScrapingWithLootTablesJeiRecipe(ModItems.DIRTY_STONE_BRICK_CHIMNEY.get(), ModItems.STONE_BRICK_CHIMNEY.get()),
                    new SootScrapingWithLootTablesJeiRecipe(ModItems.DIRTY_COPPER_CHIMNEY.get(), ModItems.COPPER_CHIMNEY.get()),
                    new SootScrapingWithLootTablesJeiRecipe(ModItems.DIRTY_TERRACOTTA_CHIMNEY.get(), ModItems.TERRACOTTA_CHIMNEY.get())
            ));
        }
        else {
            List<SootScrapingRecipe> recipes = new ArrayList<>(Objects.requireNonNull(Minecraft.getInstance().level)
                    .getRecipeManager()
                    .getAllRecipesFor(ModRecipeTypes.SOOT_SCRAPING.get()));

            recipes.sort((r, r1) -> {
                List<ChanceResult> results = r.getResults();
                List<ChanceResult> results1 = r1.getResults();
                if (results.isEmpty())
                    return results1.isEmpty() ? -1 : 0;
                if (results1.isEmpty())
                    return 1;

                return Float.compare(results1.get(0).getChance(), results.get(0).getChance());
            });

            registration.addRecipes(JeiRecipeTypes.SOOT_SCRAPING, recipes);
        }
    }
}
