package io.github.mortuusars.fabric;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import io.github.mortuusars.sootychimneys.Config;
import net.fabricmc.api.ModInitializer;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.fml.config.ModConfig;

public final class SootyChimneysFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SootyChimneys.init();

        NeoForgeConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.COMMON, Config.Common.SPEC);
        NeoForgeConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.CLIENT, Config.Client.SPEC);
    }

    public static class Tags {
        public static class Items {
            public static final TagKey<Item> SOOT_SCRAPERS = TagKey.create(Registries.ITEM, SootyChimneys.resource("soot_scrapers"));
        }
    }
}
