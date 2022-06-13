package io.github.mortuusars.sootychimneys.blocks;

import com.mojang.math.Vector3f;

public class BrickChimneyBlock extends ChimneyBlock{

    private static final Vector3f _particleOriginOffset = new Vector3f(0.5f, 1.15f, 0.5f);
    private static final Vector3f _particleMaxRandomOffset = new Vector3f(0.15f, 0.1f, 0.15f);

    public BrickChimneyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Vector3f getParticleOriginOffset() {
        return _particleOriginOffset;
    }

    @Override
    public Vector3f getParticleMaxRandomOffset() {
        return _particleMaxRandomOffset;
    }

    @Override
    public float getSmokeIntensity() {
        return 2f;
    }

    @Override
    public float getSmokeSpeed() {
        return 1.2f;
    }
}
