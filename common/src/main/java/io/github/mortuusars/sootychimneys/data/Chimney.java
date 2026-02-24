package io.github.mortuusars.sootychimneys.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import io.github.mortuusars.sootychimneys.block.ChimneyBlock;

import java.util.Map;

public interface Chimney {
    enum State {
        CLEAN,
        DIRTY;
    }

    BiMap<ChimneyBlock, ChimneyBlock> CHIMNEY_STATES_MAP = HashBiMap.create(
            Map.of(
                    SootyChimneys.Blocks.BRICK_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_BRICK_CHIMNEY.get(),
                    SootyChimneys.Blocks.COBBLESTONE_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_COBBLESTONE_CHIMNEY.get(),
                    SootyChimneys.Blocks.STONE_BRICK_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_STONE_BRICK_CHIMNEY.get(),
                    SootyChimneys.Blocks.MUD_BRICK_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_MUD_BRICK_CHIMNEY.get(),
                    SootyChimneys.Blocks.IRON_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_IRON_CHIMNEY.get(),
                    SootyChimneys.Blocks.COPPER_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_COPPER_CHIMNEY.get(),
                    SootyChimneys.Blocks.TERRACOTTA_CHIMNEY.get(), SootyChimneys.Blocks.DIRTY_TERRACOTTA_CHIMNEY.get()
            )
    );

    static ChimneyBlock getCleanBlock(ChimneyBlock dirty) {
        return CHIMNEY_STATES_MAP.inverse().get(dirty);
    }

    static ChimneyBlock getDirtyBlock(ChimneyBlock clean) {
        return CHIMNEY_STATES_MAP.get(clean);
    }
}