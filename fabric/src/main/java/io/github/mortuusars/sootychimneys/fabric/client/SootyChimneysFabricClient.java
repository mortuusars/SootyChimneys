package io.github.mortuusars.sootychimneys.fabric.client;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class SootyChimneysFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_LEVEL_TICK.register(level -> {
            if (Config.Common.WIND_ENABLED.get()) {
                Wind.update(level);
            }
        });
    }
}
