package be.artex.rolesffa.api.weather;

import java.util.Random;

public class Weather {
    private static final Random RANDOM = new Random();

    private static WeatherType currentWeather = null;

    public static void replaceCurrentWeather(WeatherType weatherType) {
        currentWeather = weatherType;
    }

    public static WeatherType getCurrentWeather() {
        return currentWeather;
    }

    public static WeatherType getRandomWeather() {
        WeatherType[] values = WeatherType.values();

        return values[RANDOM.nextInt(values.length)];
    }
}
