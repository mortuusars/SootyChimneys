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
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootCoveringRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootCoveringJeiRecipe;
import io.github.mortuusars.sootychimneys.integration.jei.recipe.SootScrapingJeiRecipe;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class SootyChimneysJeiPlugin implements IModPlugin {

    private static final ResourceLocation UID = SootyChimneys.resource("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (CommonConfig.ADD_SOOT_COVERING_TO_JEI.get() && CommonConfig.DIRTY_CHANCE.get() > 0.0D)
            registration.addRecipeCategories(new SootCoveringRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        if (CommonConfig.ADD_SOOT_SCRAPING_TO_JEI.get())
            registration.addRecipeCategories(new SootScrapingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JeiRecipeTypes.SOOT_COVERING, ImmutableList.of(
                new SootCoveringJeiRecipe(ModItems.BRICK_CHIMNEY.get(), ModItems.DIRTY_BRICK_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.STONE_BRICK_CHIMNEY.get(), ModItems.DIRTY_STONE_BRICK_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.TERRACOTTA_CHIMNEY.get(), ModItems.DIRTY_TERRACOTTA_CHIMNEY.get()),
                new SootCoveringJeiRecipe(ModItems.COPPER_CHIMNEY.get(), ModItems.DIRTY_COPPER_CHIMNEY.get())
        ));

        registration.addRecipes(JeiRecipeTypes.SOOT_SCRAPING, ImmutableList.of(
                new SootScrapingJeiRecipe(ModItems.DIRTY_BRICK_CHIMNEY.get(), ModItems.BRICK_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_STONE_BRICK_CHIMNEY.get(), ModItems.STONE_BRICK_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_TERRACOTTA_CHIMNEY.get(), ModItems.TERRACOTTA_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_COPPER_CHIMNEY.get(), ModItems.COPPER_CHIMNEY.get())
        ));
    }
}
