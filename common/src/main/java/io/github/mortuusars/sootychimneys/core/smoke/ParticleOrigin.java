package io.github.mortuusars.sootychimneys.core.smoke;

import org.joml.Vector3f;

public class ParticleOrigin extends Vector3f {
    public ParticleOrigin(float x, float y, float z) {
        super(x, y, z);
    }

    public ParticleOrigin() {
    }

    public static ParticleOrigin of(float x, float y, float z) {
        return new ParticleOrigin(x, y, z);
    }
}
