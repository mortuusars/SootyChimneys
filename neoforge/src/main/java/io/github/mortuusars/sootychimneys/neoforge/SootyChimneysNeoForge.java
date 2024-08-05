package io.github.mortuusars.sootychimneys.neoforge;

import io.github.mortuusars.sootychimneys.Config;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.stats.StatType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(SootyChimneys.ID)
public final class SootyChimneysNeoForge {
    public SootyChimneysNeoForge(ModContainer container) {
        SootyChimneys.init();

        SootyChimneys.Stats.STATS.forEach((location, formatter) -> {
            RegisterImpl.CUSTOM_STATS.register(location.getPath(), () -> location);
        });

        container.registerConfig(ModConfig.Type.COMMON, Config.Common.SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, Config.Client.SPEC);

        IEventBus modEventBus = container.getEventBus();

        assert modEventBus != null;
        RegisterImpl.BLOCKS.register(modEventBus);
        RegisterImpl.BLOCK_ENTITY_TYPES.register(modEventBus);
        RegisterImpl.ENTITY_TYPES.register(modEventBus);
        RegisterImpl.ITEMS.register(modEventBus);
        RegisterImpl.MENU_TYPES.register(modEventBus);
        RegisterImpl.RECIPE_TYPES.register(modEventBus);
        RegisterImpl.RECIPE_SERIALIZERS.register(modEventBus);
        RegisterImpl.CRITERION_TRIGGERS.register(modEventBus);
        RegisterImpl.SOUND_EVENTS.register(modEventBus);
        RegisterImpl.COMMAND_ARGUMENT_TYPES.register(modEventBus);
        RegisterImpl.WORLD_GEN_FEATURES.register(modEventBus);
        RegisterImpl.DATA_COMPONENT_TYPES.register(modEventBus);
        RegisterImpl.PARTICLE_TYPES.register(modEventBus);
        RegisterImpl.CUSTOM_STATS.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            SootyChimneysNeoForgeClient.init(container);
        }
    }
}
