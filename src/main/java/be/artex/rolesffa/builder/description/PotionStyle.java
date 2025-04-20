package be.artex.rolesffa.builder.description;

import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffectType;

public enum PotionStyle {
    SPEED(ChatColor.YELLOW, "vitesse"),
    RESISTANCE(ChatColor.GRAY, "résistance"),
    FIRE_RESISTANCE(ChatColor.GOLD, "résistance au feu");

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

    public static PotionStyle EffectTypeAsStyle(PotionEffectType type) {
        if (type.equals(PotionEffectType.DAMAGE_RESISTANCE))
            return RESISTANCE;
        else if (type.equals(PotionEffectType.SPEED))
            return SPEED;
        else if (type.equals(PotionEffectType.FIRE_RESISTANCE))
            return FIRE_RESISTANCE;

        return null;
    }
}
