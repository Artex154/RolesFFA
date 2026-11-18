package be.artex.rolesffa.api;

import org.bukkit.ChatColor;

public class Message {
    public static String generate(String string) {
        return ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " RolesFFA" + ChatColor.DARK_GRAY + " ▏ " + ChatColor.GRAY + string;
    }

    public static String cooldownTimeLeft(long timeLeft) {
        return generate("Vous êtes encore en cooldown pour " + ChatColor.AQUA + timeLeft + " secondes" + ChatColor.GRAY + ".");
    }
}
