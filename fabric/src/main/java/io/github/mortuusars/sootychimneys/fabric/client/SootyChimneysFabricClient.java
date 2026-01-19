package io.github.mortuusars.sootychimneys.fabric.client;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.data.wind.Wind;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public final class SootyChimneysFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.COBBLESTONE_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.STONE_BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.MUD_BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.IRON_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_IRON_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.COPPER_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_COPPER_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.TERRACOTTA_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SootyChimneys.Blocks.DIRTY_TERRACOTTA_CHIMNEY.get(), ChunkSectionLayer.CUTOUT);

        ClientTickEvents.END_WORLD_TICK.register(level -> {
            if (Config.Common.WIND_ENABLED.get()) {
                Wind.update(level);
            }
        });
    }
}
