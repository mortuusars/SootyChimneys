package io.github.mortuusars.sootychimneys.neoforge.event;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import io.github.mortuusars.sootychimneys.recipe.SootScrapingRecipe;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Collection;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SootyChimneys.ID)
public class ClientEvents {
    @SubscribeEvent
    public static void onCreativeTabsBuild(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(SootyChimneys.Items.BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.COBBLESTONE_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_COBBLESTONE_CHIMNEY.get());
            event.accept(SootyChimneys.Items.STONE_BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_STONE_BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.MUD_BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_MUD_BRICK_CHIMNEY.get());
            event.accept(SootyChimneys.Items.IRON_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_IRON_CHIMNEY.get());
            event.accept(SootyChimneys.Items.COPPER_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_COPPER_CHIMNEY.get());
            event.accept(SootyChimneys.Items.TERRACOTTA_CHIMNEY.get());
            event.accept(SootyChimneys.Items.DIRTY_TERRACOTTA_CHIMNEY.get());
        }
    }

    @SubscribeEvent
    public static void onRecipe(RecipesReceivedEvent event) {
        SootScrapingRecipe.clientKnownRecipes = event.getRecipeMap()
              .byType(SootyChimneys.RecipeTypes.SOOT_SCRAPING.get())
              .stream()
              .map(RecipeHolder::value)
              .toList();
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (Config.Common.WIND_ENABLED.get()) {
            Wind.update(event.getLevel());
        }
    }
}
