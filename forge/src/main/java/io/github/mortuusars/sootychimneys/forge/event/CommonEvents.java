package io.github.mortuusars.sootychimneys.forge.event;

import com.mojang.logging.LogUtils;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.forge.integration.create.CreateIntegration;
import net.minecraft.stats.Stats;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.repository.legacy.metadata.ArtifactMetadata;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SootyChimneys.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    public static final Logger LOGGER = LogUtils.getLogger();

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
            ModList.get().getModContainerById("create").ifPresent(create -> {
                ArtifactVersion requiredVersion = new DefaultArtifactVersion("6.0.7");
                ArtifactVersion currentVersion = create.getModInfo().getVersion();
                if (currentVersion.compareTo(requiredVersion) < 0) {
                    LOGGER.warn("Sooty Chimneys does not support Create '{}'. Required: '{}' or greater. Skipping compat initialization.",
                          currentVersion, requiredVersion);
                    return;
                }
                LOGGER.info("Initializing Sooty Chimneys compat with Create...");
                CreateIntegration.registerMovingBehaviors();
            });
        });
    }
}
