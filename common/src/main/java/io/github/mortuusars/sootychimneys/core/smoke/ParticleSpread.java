package io.github.mortuusars.sootychimneys.core.smoke;

import org.joml.Vector3f;

public class ParticleSpread extends Vector3f {
    public ParticleSpread(float x, float y, float z) {
        super(x, y, z);
    }

    public ParticleSpread() {
    }

    public static ParticleSpread of(float x, float y, float z) {
        return new ParticleSpread(x, y, z);
    }
}
