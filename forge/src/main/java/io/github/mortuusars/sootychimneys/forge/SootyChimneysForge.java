package io.github.mortuusars.sootychimneys.forge;

import io.github.mortuusars.sootychimneys.Config;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import io.github.mortuusars.sootychimneys.SootyChimneys;

@Mod(SootyChimneys.ID)
public final class SootyChimneysForge {
    public SootyChimneysForge(ModContainer container) {
        SootyChimneys.init();

        SootyChimneys.Stats.STATS.forEach((location, formatter) -> {
            RegisterImpl.CUSTOM_STATS.register(location.getPath(), () -> location);
        });

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.Common.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.Client.SPEC);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        assert modEventBus != null;
        RegisterImpl.BLOCKS.register(modEventBus);
        RegisterImpl.BLOCK_ENTITY_TYPES.register(modEventBus);
        RegisterImpl.ENTITY_TYPES.register(modEventBus);
        RegisterImpl.ITEMS.register(modEventBus);
        RegisterImpl.MENU_TYPES.register(modEventBus);
        RegisterImpl.RECIPE_TYPES.register(modEventBus);
        RegisterImpl.RECIPE_SERIALIZERS.register(modEventBus);
        RegisterImpl.SOUND_EVENTS.register(modEventBus);
        RegisterImpl.COMMAND_ARGUMENT_TYPES.register(modEventBus);
        RegisterImpl.WORLD_GEN_FEATURES.register(modEventBus);
        RegisterImpl.PARTICLE_TYPES.register(modEventBus);
        RegisterImpl.CUSTOM_STATS.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            SootyChimneysForgeClient.init(container);
        }
    }
}
