package io.github.mortuusars.sootychimneys.forge.event;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@SuppressWarnings("unused")
public class ClientEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = SootyChimneys.ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
    public static class Mod {
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
    }

    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = SootyChimneys.ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE)
    public static class Game {
        @SubscribeEvent
        public static void onLevelTick(TickEvent.LevelTickEvent event) {
            if (event.phase == TickEvent.Phase.END && Config.Common.WIND_ENABLED.get()) {
                Wind.update(event.level);
            }
        }
    }
}
