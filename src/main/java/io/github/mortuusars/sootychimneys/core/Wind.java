package io.github.mortuusars.sootychimneys.core;

import net.minecraft.util.Mth;

import java.util.Objects;

@SuppressWarnings("unused")
public class Wind {
    private double _angleDegrees;
    private float _strength;

    private double _xCoordinate;
    private double _yCoordinate;

    public Wind(double angleDegrees, float strength) {
        this._angleDegrees = angleDegrees;
        this._strength = strength;
    }

    public void set(double angleDegrees, float strength){
        _angleDegrees = angleDegrees;
        _strength = strength;

        double _angleRadians = getAngleInRadians();
        _xCoordinate = Math.cos(_angleRadians);
        _yCoordinate = Math.sin(_angleRadians);
    }

    public void update(double addDegrees, float addStrength){
        _angleDegrees = (_angleDegrees + addDegrees) % 360d;
        _strength = Mth.clamp(_strength + addStrength, 0.0f, 1.0f);

        double _angleRadians = getAngleInRadians();
        _xCoordinate = Math.cos(_angleRadians);
        _yCoordinate = Math.sin(_angleRadians);
    }

    public float getStrength(){
        return _strength;
    }

    public double getAngleInDegrees(){
        return _angleDegrees;
    }

    public double getAngleInRadians(){
        return _angleDegrees * (Math.PI/180);
    }

    public double getXCoordinate(){
        return _xCoordinate;
    }

    public double getYCoordinate(){
        return _yCoordinate;
    }

    @Override
    public String toString() {
        return "Wind{" + "Angle=" + String.format("%.1f", _angleDegrees) + ", Strength=" + String.format("%.2f", _strength) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return _angleDegrees == wind._angleDegrees && Float.compare(wind._strength, _strength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_angleDegrees, _strength);
    }
}

