package io.github.mortuusars.sootychimneys.neoforge;

import io.github.mortuusars.sootychimneys.Config;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(SootyChimneys.ID)
public final class SootyChimneysNeoForge {
    public SootyChimneysNeoForge(ModContainer container) {
        SootyChimneys.init();

        container.registerConfig(ModConfig.Type.LOCAL, Config.Common.SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, Config.Client.SPEC);

        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            SootyChimneysNeoForgeClient.init(container);
        }
    }
}
