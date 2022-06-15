package io.github.mortuusars.sootychimneys.client;

import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class BlockRendering {
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        setChimneysRenderType();
    }

    private static void setChimneysRenderType() {
        ItemBlockRenderTypes.setRenderLayer(Registry.BRICK_CHIMNEY.get(), RenderType.translucent());
    }
}
