package be.artex.rolesffa.api.descriptionBuilder;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.util.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescriptionBuilder {
    private final Role role;
    private boolean hasFireRes = false;
    private final List<HoverHolder> passifs = new ArrayList<>();
    private final List<HoverHolder> activables = new ArrayList<>();

    /**
     * Start of the builder.
     * @param role the role used to generate most of the base informations: name, side, effects.
     */
    public DescriptionBuilder(Role role) {
        this.role = role;
    }

    /**
     * @param has if yes or no, the role has fire resistance.
     * @return the builder to continue the chain.
     */
    public DescriptionBuilder hasFireRes(boolean has) {
        hasFireRes = has;
        return this;
    }

    /**
     * @param holders list of HoverHolders to add the list of passives.
     * @return the builder to continue the chain.
     */
    public DescriptionBuilder passifs(HoverHolder... holders) {
        Collections.addAll(passifs, holders);
        return this;
    }

    /**
     * @param holders list of HoverHolders to add the list of activable abilities.
     * @return the builder to continue the chain.
     */
    public DescriptionBuilder activables(HoverHolder... holders) {
        Collections.addAll(activables, holders);
        return this;
    }

    /**
     * Builds the description with all given information.
     * @return the description.
     */
    public TextComponent build() {
        TextComponent description = new TextComponent(" ");
        description.addExtra(" \n");
        description.addExtra(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " ▏ " + ChatColor.WHITE + "Informations\n");
        description.addExtra(" \n");
        description.addExtra(ChatColor.DARK_GRAY + " " + ChatColor.BOLD + " - " + ChatColor.GRAY + "Rôle: " + role.getType().getColor() + ChatColor.BOLD + role.getName() + "\n");
        description.addExtra(" \n");
        description.addExtra(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " ▏ " + ChatColor.WHITE + "Effets\n");
        description.addExtra(" \n");
        addEffects(description);
        description.addExtra(" \n");
        description.addExtra(" \n");
        description.addExtra(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " ▏ " + ChatColor.WHITE + "Particularités\n");
        description.addExtra(" \n");

        boolean hasPassives = !passifs.isEmpty();
        boolean showParti = false;

        if (hasPassives) {
            passifs.forEach(p -> {
                description.addExtra(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " - ");
                description.addExtra(p.toComponent());
                description.addExtra("\n");
            });

            showParti = true;
        }

        if (!activables.isEmpty()) {
            if (hasPassives)
                description.addExtra(" \n");

            activables.forEach(a -> {
                description.addExtra(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " - ");
                description.addExtra(a.toComponent());
                description.addExtra("\n");
            });

            showParti = true;
        }

        if (!showParti)
            description.addExtra(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous ne disposez d'aucune particularité.\n ");

        return description;
    }

    private void addEffects(TextComponent component) {
        List<String> strings = new ArrayList<>();

        if (role.getBonusStrength() != 0)
            strings.add(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + " - " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " " + role.getBonusStrength() + "% de Force");

        if (role.getBonusResistance() != 0)
            strings.add(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + " - " + StringUtils.RESISTANCE_SIGN + ChatColor.GRAY + " " + role.getBonusResistance() + "% de Résistance");

        if (role.getBonusSpeed() != 0)
            strings.add(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + " - " + StringUtils.SPEED_SIGN+ ChatColor.YELLOW + " " + role.getBonusSpeed() + "% de Vitesse");

        if (role.getMaxHealth() != 20)
            strings.add(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + " - " + ChatColor.RED + role.getMaxHealth() / 2 + "❤ permanents");

        if (hasFireRes)
            strings.add(ChatColor.DARK_GRAY + "  " + ChatColor.BOLD + " - " + ChatColor.GOLD  + "Résistance au Feu");

        if (strings.isEmpty()) {
            component.addExtra(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous ne disposez d'aucun effet.");
            return;
        }

        component.addExtra(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous disposez des effets suivants: ");

        strings.forEach(s -> {
            component.addExtra("\n");
            component.addExtra(s);
        });
    }
}