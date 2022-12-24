package io.github.mortuusars.sootychimneys.integration.jei;

import com.google.common.collect.ImmutableList;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.config.CommonConfig;
import io.github.mortuusars.sootychimneys.integration.jei.category.SootScrapingRecipeCategory;
import io.github.mortuusars.sootychimneys.integration.jei.resource.SootScrapingJeiRecipe;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class SootyChimneysJeiPlugin implements IModPlugin {

    private static final ResourceLocation UID = SootyChimneys.resource("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (CommonConfig.ADD_SOOT_SCRAPING_TO_JEI.get())
            registration.addRecipeCategories(new SootScrapingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(JeiRecipeTypes.SOOT_SCRAPING, ImmutableList.of(
                new SootScrapingJeiRecipe(ModItems.DIRTY_BRICK_CHIMNEY.get(), ModItems.BRICK_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_STONE_BRICK_CHIMNEY.get(), ModItems.STONE_BRICK_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_TERRACOTTA_CHIMNEY.get(), ModItems.TERRACOTTA_CHIMNEY.get()),
                new SootScrapingJeiRecipe(ModItems.DIRTY_COPPER_CHIMNEY.get(), ModItems.COPPER_CHIMNEY.get())
        ));
    }
}
