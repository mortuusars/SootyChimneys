package io.github.mortuusars.sootychimneys.data.wind;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class Wind {
    private final static WindData wind = new WindData(0, 0f);

    public static WindData getWind() {
        return wind;
    }

    public static void update(Level level) {
        RandomSource random = level.getRandom();

        WindState windState = getWindState(level);

        double addDegrees = windState.max() * (random.nextInt(2) - 1);
        float targetStrength = Mth.map(random.nextFloat(), 0f, 1f, windState.min(), windState.max());
        float strength = Mth.lerp(0.1f, wind.getStrength(), targetStrength);

        wind.set(wind.getAngleInDegrees() + addDegrees, strength);
    }

    private static WindState getWindState(Level level) {
        final int day = (int)(level.getGameTime() / 24000L);
        final TimeOfDay timeOfDay = TimeOfDay.of(level);
        final Weather weather = Weather.of(level);

        if (weather == Weather.THUNDER) {
            return WindState.STORMY;
        } else if (weather == Weather.RAIN) {
            return timeOfDay != TimeOfDay.EVENING && day % 3 > 0 ? WindState.WINDY : WindState.BREEZE;
        } else if (timeOfDay == TimeOfDay.DAY) {
            return day % 5 > 2 ? WindState.BREEZE : WindState.CALM;
        } else {
            return day % 4 == 0 ? WindState.BREEZE : WindState.CALM;
        }
    }
}
