package io.github.mortuusars.sootychimneys.core;

import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public interface ISootyChimney {
    Block getCleanVariant();
    Block getDirtyVariant();

    default boolean isDirty() {
        return getCleanVariant() != this;
    }

    default boolean isClean(){
        return !isDirty();
    }

    default void spawnSootParticles(Level level, BlockPos pos, boolean serverSide) {
        RandomSource random = level.getRandom();
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        for (int i = 0; i < random.nextInt(12, 20); i++) {
            if (serverSide && level instanceof ServerLevel serverLevel)
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE,
                        RandomOffset.offset(x, 0.8f),
                        RandomOffset.offset(y, 0.8f),
                        RandomOffset.offset(z, 0.8f),
                        1, 0,0,0, 0);
            else {
                level.addParticle(ParticleTypes.LARGE_SMOKE,
                        RandomOffset.offset(x, 0.8f),
                        RandomOffset.offset(y, 0.8f),
                        RandomOffset.offset(z, 0.8f),
                        0,0,0);
            }
        }
    }

    default float getScrapingDropChance(){
        return 0.5f;
    }
}
