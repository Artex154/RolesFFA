package be.artex.rolesffa.helper;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class StringHelper {
    public static final String DOT = ChatColor.DARK_GRAY + " • ";
    public static final String LINE = ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------------";

    public static final String SPEED_SIGN = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + ChatColor.BOLD + "⚡" + ChatColor.DARK_GRAY + "]";
    public static final String STRENGTH_SIGN = ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "⚔" + ChatColor.DARK_GRAY + "]";

    public static TextComponent speedSignHover() {
        TextComponent description = new TextComponent(SPEED_SIGN);
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(SPEED_SIGN + ChatColor.GRAY + " Pourcentages de " + ChatColor.AQUA + "Vitesse\n\n" +
                StringHelper.DOT + ChatColor.GRAY + "120% de " + ChatColor.AQUA + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.AQUA + " Speed I" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "140% de " + ChatColor.AQUA + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.AQUA + " Speed II" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "160% de " + ChatColor.AQUA + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.AQUA + " Speed III" + ChatColor.GRAY + ".\n")});

        description.setHoverEvent(event);

        return description;
    }
}
