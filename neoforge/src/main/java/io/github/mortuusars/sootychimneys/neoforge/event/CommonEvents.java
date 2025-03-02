package io.github.mortuusars.sootychimneys.neoforge.event;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.neoforge.integration.create.CreateIntegration;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SootyChimneys.ID, bus = EventBusSubscriber.Bus.MOD)
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
    public static void onEnqueueIMC(InterModEnqueueEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("create")) {
                CreateIntegration.registerMovingBehaviors();
            }
        });
    }
}
