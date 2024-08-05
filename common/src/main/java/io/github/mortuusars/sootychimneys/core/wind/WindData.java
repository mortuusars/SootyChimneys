package io.github.mortuusars.sootychimneys.core.wind;

import io.github.mortuusars.sootychimneys.Config;
import net.minecraft.util.Mth;

import java.util.Objects;

@SuppressWarnings("unused")
public class WindData {
    private double angleDegrees;
    private float strength;

    private double xCoordinate;
    private double yCoordinate;

    public WindData(double angleDegrees, float strength) {
        this.angleDegrees = angleDegrees;
        this.strength = strength;
    }

    public void set(double angleDegrees, float strength) {
        this.angleDegrees = angleDegrees;
        this.strength = strength;

        double _angleRadians = getAngleInRadians();
        xCoordinate = Math.cos(_angleRadians);
        yCoordinate = Math.sin(_angleRadians);
    }

    public void update(double addDegrees, float addStrength) {
        angleDegrees = (angleDegrees + addDegrees) % 360d;
        strength = Mth.clamp(strength + addStrength, 0.0f, 1.0f);

        double angleRadians = getAngleInRadians();
        xCoordinate = Math.cos(angleRadians);
        yCoordinate = Math.sin(angleRadians);
    }

    /**
     * @return Untouched strength value from wind calculation.
     */
    public float getStrength() {
        return strength;
    }

    /**
     * @return Strength value, suitable for smoke particles. Takes into account config settings.
     */
    public float getAdjustedStrength() {
        return (getStrength() * 0.1f) * Config.Common.WIND_STRENGTH.get().floatValue();
    }

    public double getAngleInDegrees() {
        return angleDegrees;
    }

    public double getAngleInRadians() {
        return angleDegrees * (Math.PI / 180);
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return "Wind{" + "Angle=" + String.format("%.1f", angleDegrees) + ", Strength=" + String.format("%.2f", strength) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WindData wind = (WindData) o;
        return angleDegrees == wind.angleDegrees && Float.compare(wind.strength, strength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angleDegrees, strength);
    }
}

