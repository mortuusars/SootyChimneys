package io.github.mortuusars.sootychimneys.fabric;

import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.fabric.integration.create.CreateIntegration;
import net.fabricmc.api.ModInitializer;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

import java.util.Optional;

public final class SootyChimneysFabric implements ModInitializer {

    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        SootyChimneys.init();

        ForgeConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.COMMON, Config.Common.SPEC);
        ForgeConfigRegistry.INSTANCE.register(SootyChimneys.ID, ModConfig.Type.CLIENT, Config.Client.SPEC);

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

        FabricLoader.getInstance().getModContainer("create")
              .ifPresent(create -> {
                  try {
                      Version currentVersion = create.getMetadata().getVersion();
                      Version requiredVersion = Version.parse("6.0.7");
                      if (currentVersion.compareTo(requiredVersion) < 0) {
                          LOGGER.warn("Sooty Chimneys does not support Create '{}'. Required: '{}' or greater. Skipping compat initialization.",
                                currentVersion.getFriendlyString(), requiredVersion.getFriendlyString());
                          return;
                      }
                      LOGGER.info("Initializing Sooty Chimneys compat with Create...");
                      CreateIntegration.registerMovingBehaviors();
                  } catch (VersionParsingException e) {
                      LOGGER.error("Sooty Chimneys cannot check for create version. Skipping compat initialization.");
                  }
              });
    }

    public static class Tags {
        public static class Items {
            public static final TagKey<Item> SOOT_SCRAPERS = TagKey.create(Registries.ITEM, SootyChimneys.resource("soot_scrapers"));
        }
    }
}
