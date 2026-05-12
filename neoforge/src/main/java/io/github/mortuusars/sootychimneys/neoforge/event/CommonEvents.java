package io.github.mortuusars.sootychimneys.neoforge.event;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.neoforge.integration.create.CreateIntegration;
import net.minecraft.stats.Stats;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = SootyChimneys.ID, bus = EventBusSubscriber.Bus.MOD)
public class CommonEvents {
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
