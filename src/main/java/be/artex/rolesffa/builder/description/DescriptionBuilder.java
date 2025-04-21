package be.artex.rolesffa.builder.description;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.Role;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DescriptionBuilder {
    private final String name;

    private double strength = 0;
    private final HashMap<PotionStyle, Integer> effects = new HashMap<>();
    private final ArrayList<ItemHolder> items = new ArrayList<>();
    private final ArrayList<String> customs = new ArrayList<>();
    private String onHit = null;
    private String onKill = null;

    public DescriptionBuilder(String name) {
        this.name = name;
    }

    public DescriptionBuilder strength(double strength) {
        this.strength = strength;
        return this;
    }

    public DescriptionBuilder effect(PotionEffect... effects) {
        for (PotionEffect effect : effects) {
            this.effects.put(PotionStyle.EffectTypeAsStyle(effect.getType()), effect.getAmplifier());
        }

        return this;
    }

    public DescriptionBuilder item(ItemHolder... items) {
        Collections.addAll(this.items, items);
        return this;
    }

    public DescriptionBuilder custom(String... customs) {
        Collections.addAll(this.customs, customs);
        return this;
    }

    public DescriptionBuilder onHit(String onHit) {
        this.onHit = onHit;
        return this;
    }

    public DescriptionBuilder onKill(String onKill) {
        this.onKill = onKill;
        return this;
    }

    public DescriptionBuilder role(Role role) {
        for (PotionEffect effect : role.getEffects()) {
            effects.put(PotionStyle.EffectTypeAsStyle(effect.getType()), effect.getAmplifier());
        }

        items.addAll(role.getItems());

        this.strength = role.getStrength();

        return this;
    }

    public TextComponent build() {
        TextComponent text = new TextComponent(Main.line);
        text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Rôle: " + this.name + ChatColor.GRAY + ".");
        text.addExtra("\n");

        if (strength == 0) {
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous possédez " + ChatColor.RED + "+" + ((this.strength - 10) * 10) + "% de force" + ChatColor.GRAY + " de façon permanente.");
        } else {
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous ne possédez aucune " + ChatColor.RED + "force" + ChatColor.GRAY + " supplémentaire.");
        }

        text.addExtra("\n");

        if (effects.isEmpty()) {
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous ne possédez aucun effet.");
        } else {
            effects.forEach((style, amplifier) ->
                text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous possédez " + style.getColor() + style.getName() + " " + (amplifier + 1) + ChatColor.GRAY + ".")
            );
        }

        text.addExtra("\n");

        if (items.isEmpty()) {
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous ne possédez aucun item.");
        } else {
            items.forEach(item -> {
                text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Vous possédez ");
                text.addExtra(item.getItem().getDescription());
                text.addExtra(ChatColor.GRAY + ".");
            });
        }

        if (!customs.isEmpty()) {
            for (String custom : customs) {
                text.addExtra("\n");
                text.addExtra("\n" + Main.dot + custom);
            }
        }

        if (onHit != null) {
            text.addExtra("\n");
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Quand vous " + ChatColor.RED + "tapperez " + ChatColor.GRAY + "une personne, " + this.onHit + ChatColor.GRAY + ".");
        }

        if (onKill != null) {
            text.addExtra("\n");
            text.addExtra("\n" + Main.dot + ChatColor.GRAY + "Quand vous " + ChatColor.RED + "tuerez " + ChatColor.GRAY + "une personne, " + this.onKill + ChatColor.GRAY + ".");
        }

        text.addExtra("\n");
        text.addExtra(Main.line);

        return text;
    }
}
