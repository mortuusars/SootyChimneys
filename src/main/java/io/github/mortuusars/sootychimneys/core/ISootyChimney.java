package io.github.mortuusars.sootychimneys.core;

import io.github.mortuusars.sootychimneys.utils.RandomOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Random;

public interface ISootyChimney {
    Block getCleanVariant();
    Block getDirtyVariant();

    default boolean isDirty() {
        return getCleanVariant() != this;
    }

    default boolean isClean(){
        return !isDirty();
    }

    default void makeSootParticles(Level level, BlockPos pos) {
        RandomSource random = level.getRandom();
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        for (int i = 0; i < random.nextInt(12, 20); i++) {
            level.addParticle(ParticleTypes.LARGE_SMOKE,
                    RandomOffset.offset(x, 1.2d),
                    RandomOffset.offset(y, 1.2d),
                    RandomOffset.offset(z, 1.2d),
                    0,0,0);
        }
    }

    default float getScrapingDropChance(){
        return 0.5f;
    }
}
