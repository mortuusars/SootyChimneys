package io.github.mortuusars.fabric.client;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.core.wind.Wind;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class SootyChimneysFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_WORLD_TICK.register(level -> {
            if (Config.Common.WIND_ENABLED.get()) {
                Wind.update(level);
            }
        });
    }
}
