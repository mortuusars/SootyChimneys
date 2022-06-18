package io.github.mortuusars.sootychimneys.client;

import net.minecraftforge.eventbus.api.IEventBus;

public class ClientSetup {
    private final IEventBus _modEventBus;
    public ClientSetup(IEventBus modEventBus) {
        _modEventBus = modEventBus;
    }

    public void registerClientOnlyEvents(){
        _modEventBus.register(BlockRendering.class);
    }
}
