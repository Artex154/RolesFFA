package be.artex.rolesffa.util.builder;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;

public class DescriptionBuilder {
    private final String name;

    private double strength = 0;
    private HashMap<PotionStyle, Integer> effects = null;
    private List<SPItem> items = null;

    public DescriptionBuilder(String name) {
        this.name = name;
    }

    public DescriptionBuilder strength(double strength) {
        this.strength = strength;
        return this;
    }

    public DescriptionBuilder effect(List<PotionEffect> effects) {
        this.effects = new HashMap<>();

        for (PotionEffect effect : effects) {
            if (effect.getType().equals(PotionEffectType.DAMAGE_RESISTANCE))
                this.effects.put(PotionStyle.RESISTANCE, effect.getAmplifier());
            else if (effect.getType().equals(PotionEffectType.SPEED))
                this.effects.put(PotionStyle.SPEED, effect.getAmplifier());
        }

        return this;
    }

    public DescriptionBuilder item(List<SPItem> items) {
        this.items = items;
        return this;
    }

    public TextComponent build() {
        TextComponent text = new TextComponent(StringUtils.line);
        text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Rôle: " + this.name + ChatColor.GRAY + ".\n");
        text.addExtra("\n");

        if (!(strength <= 0)) {
            text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + " Vous possédez " + ChatColor.RED + "+" + this.strength + "%" + ChatColor.GRAY + "de façon permanente.");
            text.addExtra("\n");
        }

        if (effects != null) {
            effects.forEach((effectStyle, amplifier) -> {
                text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Vous possédez " + effectStyle.getColor() + effectStyle.getName() + " " + (amplifier + 1) + ChatColor.GRAY + ".");
            });

            text.addExtra("\n");
        }

        if (items != null) {
            items.forEach(item -> {
                text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Vous possédez ");
                text.addExtra(item.getDescription());
                text.addExtra(ChatColor.GRAY + ".");
            });

            text.addExtra("\n");
        }

        text.addExtra(StringUtils.line);

        return text;
    }
}
