package io.github.mortuusars.sootychimneys.core;

import com.mojang.math.Vector3f;

public class ChimneySmokeProperties {
    private final Vector3f particleOrigin;
    private final Vector3f particleSpread;
    private float intensity = 1f;
    private float speed = 1f;

    @SuppressWarnings("unused")
    public ChimneySmokeProperties(Vector3f particleOrigin, Vector3f particleSpread) {
        this.particleOrigin = particleOrigin;
        this.particleSpread = particleSpread;
    }

    public ChimneySmokeProperties(float particleOriginX, float particleOriginY, float particleOriginZ,
                                  float particleSpreadX, float particleSpreadY, float particleSpreadZ) {
        this.particleOrigin = new Vector3f(particleOriginX, particleOriginY, particleOriginZ);
        this.particleSpread = new Vector3f(particleSpreadX, particleSpreadY, particleSpreadZ);
    }

    public ChimneySmokeProperties setIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public ChimneySmokeProperties setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public Vector3f getParticleOrigin(){
        return particleOrigin;
    }

    public Vector3f getParticleSpread(){
        return particleSpread;
    }

    public float getIntensity(){
        return intensity;
    }

    public float getSpeed(){
        return speed;
    }
}
