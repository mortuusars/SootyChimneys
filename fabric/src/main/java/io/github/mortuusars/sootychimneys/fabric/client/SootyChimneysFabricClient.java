package io.github.mortuusars.sootychimneys.fabric.client;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.renderer.RenderType;

public final class SootyChimneysFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.COBBLESTONE_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.STONE_BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.MUD_BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.IRON_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_IRON_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.COPPER_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_COPPER_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.TERRACOTTA_CHIMNEY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SootyChimneys.Blocks.DIRTY_TERRACOTTA_CHIMNEY.get(), RenderType.cutout());

        ClientTickEvents.END_WORLD_TICK.register(level -> {
            if (Config.Common.WIND_ENABLED.get()) {
                Wind.update(level);
            }
        });
    }
}
