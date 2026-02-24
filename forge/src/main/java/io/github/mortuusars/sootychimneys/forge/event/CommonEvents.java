package io.github.mortuusars.sootychimneys.forge.event;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.forge.integration.create.CreateIntegration;
import net.minecraft.stats.Stats;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SootyChimneys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
