package io.github.mortuusars.sootychimneys.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ChimneySmokeParticle extends CampfireSmokeParticle {
    public ChimneySmokeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, boolean signal) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, signal);
    }

    @Override
    public void move(double x, double y, double z) {
//        if (Config.Common.WIND_ENABLED.get()) {
//            Vector3f wind = NewWind.getWind(Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(false));
//            super.move(x + wind.x/* * ((double) age / lifetime)*/, Math.max(y, y + wind.y * 0.15f), z + wind.z/* * ((double) age / lifetime)*/);
//        } else {
            super.move(x, y, z);
//        }
    }

    public static class CosyProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public CosyProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ChimneySmokeParticle particle = new ChimneySmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, false);
            particle.setAlpha(0.9F);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }

    public static class SignalProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public SignalProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ChimneySmokeParticle particle = new ChimneySmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, true);
            particle.setAlpha(0.95F);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
