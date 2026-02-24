package io.github.mortuusars.sootychimneys.data.wind;

public enum WindState {
    CALM(0f, 0.05f),
    BREEZE(0.05f, 0.15f),
    WINDY(0.15f, 0.5f),
    STORMY(0.5f, 1f);

    private final float min;
    private final float max;

    WindState(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float min() {
        return min;
    }

    public float max() {
        return max;
    }
}
