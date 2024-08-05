package io.github.mortuusars.sootychimneys.mixin;

import io.github.mortuusars.sootychimneys.Config;
import io.github.mortuusars.sootychimneys.core.wind.Wind;
import io.github.mortuusars.sootychimneys.core.wind.WindData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlock.class)
public class CampfireParticleMixin {
    @Inject(method = "makeParticles", at = @At("HEAD"), cancellable = true)
    private static void onMakeParticles(Level level, BlockPos pos, boolean isSignalFire, boolean spawnExtraSmoke, CallbackInfo ci) {
        if (!Config.Common.WIND_ENABLED.get() || !Config.Common.WIND_AFFECTS_CAMPFIRE.get()) {
            return;
        }

        WindData wind = Wind.getWind();
        float strength = wind.getAdjustedStrength();
        double xSpeed = wind.getXCoordinate() * strength;
        double zSpeed = wind.getYCoordinate() * strength;

        RandomSource randomSource = level.getRandom();
        SimpleParticleType simpleParticleType = isSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        level.addAlwaysVisibleParticle(
                simpleParticleType,
                true,
                (double)pos.getX() + 0.5 + randomSource.nextDouble() / 3.0 * (double)(randomSource.nextBoolean() ? 1 : -1),
                (double)pos.getY() + randomSource.nextDouble() + randomSource.nextDouble(),
                (double)pos.getZ() + 0.5 + randomSource.nextDouble() / 3.0 * (double)(randomSource.nextBoolean() ? 1 : -1),
                xSpeed,
                0.07,
                zSpeed
        );

        if (spawnExtraSmoke) {
            level.addParticle(
                    ParticleTypes.SMOKE,
                    (double)pos.getX() + 0.5 + randomSource.nextDouble() / 4.0 * (double)(randomSource.nextBoolean() ? 1 : -1),
                    (double)pos.getY() + 0.4,
                    (double)pos.getZ() + 0.5 + randomSource.nextDouble() / 4.0 * (double)(randomSource.nextBoolean() ? 1 : -1),
                    xSpeed,
                    0.005,
                    zSpeed
            );
        }

        ci.cancel();
    }
}
