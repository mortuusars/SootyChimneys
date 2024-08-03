package io.github.mortuusars.sootychimneys.block;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.SootyChimneys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ChimneyBlockEntity extends BlockEntity {
    public ChimneyBlockEntity(BlockPos pos, BlockState blockState) {
        super(SootyChimneys.BlockEntityTypes.CHIMNEY.get(), pos, blockState);
    }

    public static <T extends BlockEntity> void particleTick(Level level, BlockPos blockPos, BlockState blockState, T ignoredT) {
        if (level.getRandom().nextDouble() < Config.Common.SMOKE_STRENGTH.get()
                && blockState.getBlock() instanceof ChimneyBlock chimney
                && chimney.shouldEmitSmoke(blockState, level, blockPos)) {
            ParticleOptions particle = chimney.getParticle(blockState, level, blockPos);
            chimney.emitParticle(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, particle);
        }
    }
}
