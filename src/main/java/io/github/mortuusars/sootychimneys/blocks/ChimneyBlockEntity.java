package io.github.mortuusars.sootychimneys.blocks;

import io.github.mortuusars.sootychimneys.setup.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChimneyBlockEntity extends BlockEntity {
    public ChimneyBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(Registry.CHIMNEY_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    public static <T extends BlockEntity> void particleTick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ChimneyBlock.emitParticles(level, blockPos, blockState);
    }
}
