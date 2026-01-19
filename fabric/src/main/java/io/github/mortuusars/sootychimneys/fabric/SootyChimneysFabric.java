package io.github.mortuusars.sootychimneys.fabric;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import io.github.mortuusars.sootychimneys.Config;
import net.fabricmc.api.ModInitializer;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.fml.config.ModConfig;

public final class SootyChimneysFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SootyChimneys.init();

        ConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.COMMON, Config.Common.SPEC);
        ConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.CLIENT, Config.Client.SPEC);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.accept(SootyChimneys.Items.BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.COBBLESTONE_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_COBBLESTONE_CHIMNEY.get());
            content.accept(SootyChimneys.Items.STONE_BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_STONE_BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.MUD_BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_MUD_BRICK_CHIMNEY.get());
            content.accept(SootyChimneys.Items.IRON_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_IRON_CHIMNEY.get());
            content.accept(SootyChimneys.Items.COPPER_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_COPPER_CHIMNEY.get());
            content.accept(SootyChimneys.Items.TERRACOTTA_CHIMNEY.get());
            content.accept(SootyChimneys.Items.DIRTY_TERRACOTTA_CHIMNEY.get());
        });

        SootyChimneys.Stats.register();
    }

    public static class Tags {
        public static class Items {
            public static final TagKey<Item> SOOT_SCRAPERS = TagKey.create(Registries.ITEM, SootyChimneys.resource("soot_scrapers"));
        }
    }
}
