package be.artex.rolesffa.roles.tank.gaara;

import be.artex.rolesffa.api.descriptionBuilder.DescriptionBuilder;
import be.artex.rolesffa.api.descriptionBuilder.HoverHolder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class Gaara extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.SAND)
            .name(ChatColor.BLUE + "" + ChatColor.BOLD + "Gaara")
            .lore(" ",
                    ChatColor.BLUE + "" + ChatColor.BOLD + " Gaara" + ChatColor.GRAY + " dispose de son " + ChatColor.YELLOW + ChatColor.BOLD + "Armure de Sable" + ChatColor.GRAY + " et de " + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.GRAY + ".     ", " ")
            .build();

    @Override
    public String getName() {
        return "Gaara";
    }

    @Override
    public String getIdentifier() {
        return "gaara";
    }

    @Override
    public ItemStack getStack() {
        return STACK.clone();
    }

    @Override
    public RoleType getType() {
        return RoleType.TANK;
    }

    @Override
    public TextComponent getDescription() {
        DescriptionBuilder builder = new DescriptionBuilder(this);

        HoverHolder sand = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "Unités de Sable" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.YELLOW + ChatColor.BOLD + "Unités de Sable" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous disposez de " + ChatColor.YELLOW + "200 unités de sable" + ChatColor.WHITE + ".\n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous " + ChatColor.YELLOW + "en" + ChatColor.WHITE + " régénérez " + ChatColor.YELLOW + "une" + ChatColor.WHITE + ChatColor.BOLD + " toutes les 0.5s" + ChatColor.WHITE + ".\n "
        );

        HoverHolder armor = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "Armure de Sable" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.YELLOW + ChatColor.BOLD + "Armure de Sable" + ChatColor.DARK_GRAY + "]\n \n" +
                ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "A chaque fois que vous prenez un" + ChatColor.RED + " coup" + ChatColor.WHITE + ", vous consommez      \n     " + ChatColor.YELLOW + "6 unités de sable " + ChatColor.WHITE + "mais aurez " + StringUtils.RESISTANCE_SIGN + ChatColor.GRAY + " 10% de résistance " + ChatColor.WHITE + "pour celui-ci.\n ");

        HoverHolder shukaku = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.GREEN + "Activable" + ChatColor.WHITE + " et " + ChatColor.RED + "désactivable" + ChatColor.WHITE + ".       \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Au plus " + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku " + ChatColor.WHITE + "est" + ChatColor.GREEN + " activé" + ChatColor.WHITE + ", au plus vous aurez de " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + " et " + ChatColor.RED + "malus" + ChatColor.WHITE + ".       \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous gardez les " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + " et " + ChatColor.RED + "malus" + ChatColor.WHITE + " des stades précédents.     \n \n" +
                        ChatColor.YELLOW + ChatColor.BOLD + "    0s - 59s:\n " +
                        ChatColor.GREEN + ChatColor.BOLD + "     Bonus: " + ChatColor.WHITE + "régénération de " + ChatColor.YELLOW + "sable" + ChatColor.WHITE + " à hauteur de " + ChatColor.YELLOW + ChatColor.BOLD + "2u/0,5s\n" +
                        ChatColor.RED + ChatColor.BOLD + "      Malus: " + ChatColor.WHITE + "aucun\n \n" +
                        ChatColor.YELLOW + ChatColor.BOLD + "    1m - 2m29:\n " +
                        ChatColor.GREEN + ChatColor.BOLD + "     Bonus: " + StringUtils.RESISTANCE_SIGN + ChatColor.GRAY + " 15% de Résistance" + ChatColor.WHITE + " avec les coups reçu avec votre " + ChatColor.YELLOW + ChatColor.BOLD + "Armure" + ChatColor.WHITE + ".\n" +
                        ChatColor.RED + ChatColor.BOLD + "      Malus: " + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.WHITE + " a " + ChatColor.BOLD + "25% " + ChatColor.WHITE + "de consommer une" + ChatColor.YELLOW + " pomme en or " + ChatColor.WHITE + "quand vous en mangez une.       \n \n" +
                        ChatColor.YELLOW + ChatColor.BOLD + "    2m30 - 4m29:\n " +
                        ChatColor.GREEN + ChatColor.BOLD + "     Bonus: " + ChatColor.WHITE + "régénération de " + ChatColor.YELLOW + "sable" + ChatColor.WHITE + " à hauteur de " + ChatColor.YELLOW + ChatColor.BOLD + "3u/0,5s\n" +
                        ChatColor.RED + ChatColor.BOLD + "      Malus: " + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.WHITE + " a " + ChatColor.BOLD + "50% " + ChatColor.WHITE + "de consommer une" + ChatColor.YELLOW + " pomme en or " + ChatColor.WHITE + "au lieu de 25%.       \n \n" +
                        ChatColor.YELLOW + ChatColor.BOLD + "    4m30:\n " +
                        ChatColor.GREEN + ChatColor.BOLD + "     Bonus: " + StringUtils.RESISTANCE_SIGN + ChatColor.GRAY + " 20% de Résistance" + ChatColor.WHITE + " avec les coups reçu avec votre " + ChatColor.YELLOW + ChatColor.BOLD + "Armure" + ChatColor.WHITE + ".\n" +
                        ChatColor.RED + ChatColor.BOLD + "      Malus: " + ChatColor.YELLOW + ChatColor.BOLD + "Shukaku" + ChatColor.WHITE + " a " + ChatColor.BOLD + "75% " + ChatColor.WHITE + "de consommer une" + ChatColor.YELLOW + " pomme en or " + ChatColor.WHITE + "au lieu de 50%.       \n"
        );

        return builder.passifs(sand, armor).activables(shukaku).build();
    }

    @Override
    public List<RFItem> getItems() {
        return Collections.singletonList(new Shukaku());
    }
}
