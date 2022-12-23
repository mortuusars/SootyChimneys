package io.github.mortuusars.sootychimneys.integration.jei;

import com.google.common.collect.ImmutableList;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.integration.jei.resource.SootScrapingJeiRecipe;
import io.github.mortuusars.sootychimneys.setup.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SootyChimneysJeiPlugin implements IModPlugin {

    private static final ResourceLocation UID = SootyChimneys.resource("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        ModItems.CHIMNEYS.forEach(chimneyRegistryObj -> {
            Item chimney = chimneyRegistryObj.get();
            if (chimney.getRegistryName().getPath().startsWith("dirty_")) {
                registration.addRecipeCatalyst(new ItemStack(chimney), JeiRecipeTypes.SOOT_SCRAPING);
            }
        });
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
