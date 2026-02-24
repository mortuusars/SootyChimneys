package io.github.mortuusars.sootychimneys.integration.jei;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootCoveringRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import io.github.mortuusars.sootychimneys.recipe.ingredient.ChanceResult;
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
        if (Config.Client.ADD_SOOT_COVERING_TO_JEI.get()) {
            registration.addRecipeCategories(new SootCoveringRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        }

        if (Config.Client.ADD_SOOT_SCRAPING_TO_JEI.get()) {
            registration.addRecipeCategories(new SootScrapingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        }
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        if (Config.Client.ADD_SOOT_COVERING_TO_JEI.get()) {
            registration.addRecipes(JeiRecipeTypes.SOOT_COVERING, List.of(
                    new SootCoveringJeiRecipe(SootyChimneys.Items.BRICK_CHIMNEY.get(), SootyChimneys.Items.DIRTY_BRICK_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.COBBLESTONE_CHIMNEY.get(), SootyChimneys.Items.DIRTY_COBBLESTONE_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.STONE_BRICK_CHIMNEY.get(), SootyChimneys.Items.DIRTY_STONE_BRICK_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.MUD_BRICK_CHIMNEY.get(), SootyChimneys.Items.DIRTY_MUD_BRICK_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.IRON_CHIMNEY.get(), SootyChimneys.Items.DIRTY_IRON_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.COPPER_CHIMNEY.get(), SootyChimneys.Items.DIRTY_COPPER_CHIMNEY.get()),
                    new SootCoveringJeiRecipe(SootyChimneys.Items.TERRACOTTA_CHIMNEY.get(), SootyChimneys.Items.DIRTY_TERRACOTTA_CHIMNEY.get())));
        }

        List<SootScrapingRecipe> recipes = new ArrayList<>(Objects.requireNonNull(Minecraft.getInstance().level)
                .getRecipeManager()
                .getAllRecipesFor(SootyChimneys.RecipeTypes.SOOT_SCRAPING.get()));

        recipes.sort((r, r1) -> {
            List<ChanceResult> results = r.getResults();
            List<ChanceResult> results1 = r1.getResults();
            if (results.isEmpty())
                return results1.isEmpty() ? -1 : 0;
            if (results1.isEmpty())
                return 1;

            return Float.compare(results1.getFirst().getChance(), results.getFirst().getChance());
        });

        registration.addRecipes(JeiRecipeTypes.SOOT_SCRAPING, recipes);
    }
}
