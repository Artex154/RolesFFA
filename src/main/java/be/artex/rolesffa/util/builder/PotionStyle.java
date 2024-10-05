package be.artex.rolesffa.util.builder;

import org.bukkit.ChatColor;

public enum PotionStyle {
    SPEED(ChatColor.YELLOW, "vitesse"),
    RESISTANCE(ChatColor.GRAY, "résistance");

    private final ChatColor color;
    private final String name;

    PotionStyle(ChatColor color, String name) {
        this.color = color;
        this.name = name;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
