package be.artex.rolesffa.helper;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class StringHelper {
    public static final String DOT = ChatColor.DARK_GRAY + " • ";
    public static final String LINE = ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------------";

    public static final String SPEED_SIGN = ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "⚡" + ChatColor.DARK_GRAY + "]";
    public static final String STRENGTH_SIGN = ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "⚔" + ChatColor.DARK_GRAY + "]";
    public static final String RESISTANCE_SIGN = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "☰" + ChatColor.DARK_GRAY + "]";

    public static TextComponent speedSignHover() {
        TextComponent description = new TextComponent(SPEED_SIGN);
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(SPEED_SIGN + ChatColor.GRAY + " Pourcentages de " + ChatColor.YELLOW + "Vitesse\n\n" +
                StringHelper.DOT + ChatColor.GRAY + "120% de " + ChatColor.YELLOW + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.YELLOW + " Vitesse I" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "140% de " + ChatColor.YELLOW + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.YELLOW + " Vitesse II" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "160% de " + ChatColor.YELLOW + "Vitesse " + ChatColor.GRAY + "->" + ChatColor.YELLOW + " Vitesse III" + ChatColor.GRAY + ".\n")});

        description.setHoverEvent(event);

        return description;
    }

    public static TextComponent strengthSignHover() {
        TextComponent description = new TextComponent(STRENGTH_SIGN);
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(STRENGTH_SIGN + ChatColor.GRAY + " Pourcentages de " + ChatColor.RED + "Force\n\n" +
                StringHelper.DOT + ChatColor.GRAY + "115% de " + ChatColor.RED + "Force " + ChatColor.GRAY + "->" + ChatColor.RED + " Force I" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "130% de " + ChatColor.RED + "Force " + ChatColor.GRAY + "->" + ChatColor.RED + " Force II" + ChatColor.GRAY + ".\n" +
                StringHelper.DOT + ChatColor.GRAY + "145% de " + ChatColor.RED + "Force " + ChatColor.GRAY + "->" + ChatColor.RED + " Force III" + ChatColor.GRAY + ".\n")});

        description.setHoverEvent(event);

        return description;
    }

    public static TextComponent resistanceSignHover() {
        TextComponent description = new TextComponent(RESISTANCE_SIGN);
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(RESISTANCE_SIGN + ChatColor.GRAY + " Pourcentages de Resistance\n\n" +
                StringHelper.DOT + ChatColor.GRAY + "120% de Résistance -> Résistance I.\n" +
                StringHelper.DOT + ChatColor.GRAY + "140% de Résistance -> Résistance II.\n" +
                StringHelper.DOT + ChatColor.GRAY + "160% de Résistance -> Résistance III.\n")});

        description.setHoverEvent(event);

        return description;
    }
}
