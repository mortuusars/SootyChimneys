package io.github.mortuusars.sootychimneys.data.wind;

import net.minecraft.core.BlockPos;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;

public enum TimeOfDay {
    MORNING,
    DAY,
    EVENING,
    NIGHT;

    public static TimeOfDay fromSunAngle(double degrees){
        if (degrees <= 290 && degrees > 270)
            return TimeOfDay.MORNING;
        else if (degrees <= 270 && degrees > 95)
            return TimeOfDay.NIGHT;
        else if (degrees <= 95 && degrees > 75)
            return TimeOfDay.EVENING;
        else
            return TimeOfDay.DAY;
    }

    public static TimeOfDay of(Level level){
        return fromSunAngle(level.environmentAttributes().getValue(EnvironmentAttributes.SUN_ANGLE, BlockPos.ZERO));
    }
}
