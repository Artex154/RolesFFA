package be.artex.rolesffa.api;

import org.bukkit.ChatColor;

public class Message {
    public static String generate(String string) {
        return ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " All Stars Party" + ChatColor.GRAY + " ▏ " + ChatColor.GRAY + string;
    }

    public static String cooldownTimeLeft(long timeLeft) {
        return generate("Vous êtes encore en cooldown pour " + ChatColor.AQUA + timeLeft + " secondes" + ChatColor.GRAY + ".");
    }
}
