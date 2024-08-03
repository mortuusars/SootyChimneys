package io.github.mortuusars.sootychimneys.core.smoke;

public class SmokeProperties {
    private final ParticleOrigin particleOrigin;
    private final ParticleSpread particleSpread;
    private float intensity = 1f;
    private float speed = 1f;

    public SmokeProperties(ParticleOrigin particleOrigin, ParticleSpread particleSpread) {
        this.particleOrigin = particleOrigin;
        this.particleSpread = particleSpread;
    }

    public SmokeProperties withIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public SmokeProperties withSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public ParticleOrigin getParticleOrigin(){
        return particleOrigin;
    }

    public ParticleSpread getParticleSpread(){
        return particleSpread;
    }

    public float getIntensity(){
        return intensity;
    }

    public float getSpeed(){
        return speed;
    }
}
