package be.artex.rolesffa.api.weather;

import org.bukkit.ChatColor;

public enum WeatherType {
    SUNNY("Ensoleillé", ChatColor.GOLD),
    RAINING("Pluvieux", ChatColor.BLUE),
    FOG("Brumeux", ChatColor.DARK_AQUA),
    THUNDER("Orageux", ChatColor.YELLOW),
    SNOWY("Neigeux", ChatColor.AQUA);

    private final String name;
    private final ChatColor color;

    WeatherType(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
