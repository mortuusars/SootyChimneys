package io.github.mortuusars.sootychimneys.neoforge.event;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.neoforge.integration.create.CreateIntegration;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SootyChimneys.ID)
public class CommonEvents {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Makes stats show up in stat screen
            SootyChimneys.Stats.STATS.forEach((location, statFormatter) -> {
                Stats.CUSTOM.get(location);
            });
        });
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        // Sync recipes to the client, so we can display them in JEI. It was easier before...
        event.sendRecipes(SootyChimneys.RecipeTypes.SOOT_SCRAPING.get());
    }

    @SubscribeEvent
    public static void onEnqueueIMC(InterModEnqueueEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("create")) {
                CreateIntegration.registerMovingBehaviors();
            }
        });
    }
}
