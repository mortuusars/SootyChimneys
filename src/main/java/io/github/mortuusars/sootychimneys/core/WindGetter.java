package io.github.mortuusars.sootychimneys.core;

import io.github.mortuusars.sootychimneys.config.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WindGetter {
    private final static Wind _wind = new Wind(0, 0f);

    public static Wind getWind(){
        return _wind;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (!Config.WIND_ENABLED.get())
            return;

        Level level = event.player.level();
        if (level.isClientSide() && level.getGameTime() % 5L == 0)
            updateWind(level);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent event){
        Level level = event.getLevel();
        event.getEntity().displayClientMessage(Component.literal(_wind +
                "\nTime of day: " + TimeOfDay.of(level) +
                "\nWeather: " + Weather.of(level)), true);
    }

    private static void updateWind(Level level) {
        RandomSource random = level.random;
        double addDegrees = Math.exp(random.nextDouble() * 3.5f) * (random.nextBoolean() ? -1 : 1);
        _wind.update(addDegrees, getWindStrengthChange(level));
    }

    private static float getWindStrengthChange(Level level){
        final TimeOfDay timeOfDay = TimeOfDay.of(level);
        final Weather weather = Weather.of(level);
        final RandomSource random = level.getRandom();

        if (weather == Weather.THUNDER)
            return random.nextFloat() * 0.2f - 0.1f + Math.max(0.0f, 0.4f - _wind.getStrength());
        else if (weather == Weather.RAIN)
            return random.nextFloat() * 0.18f - 0.09f + Math.max(0.0f, 0.2f - _wind.getStrength()) - Math.max(0.0f, _wind.getStrength() - 0.6f);
        else if (timeOfDay != TimeOfDay.DAY)
            return random.nextFloat() * 0.08f - 0.04f + Math.max(0.0f, 0.008f - _wind.getStrength()) - Math.max(0.0f, _wind.getStrength() - 0.2f);
        else
            return random.nextFloat() * 0.1f - 0.05f + Math.max(0.0f, 0.008f - _wind.getStrength()) - Math.max(0.0f, _wind.getStrength() - 0.3f);
    }
}
