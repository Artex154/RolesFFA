package be.artex.rolesffa.roles.technique.hina;

import be.artex.rolesffa.api.descriptionBuilder.DescriptionBuilder;
import be.artex.rolesffa.api.descriptionBuilder.HoverHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.weather.WeatherType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Hina extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.WATER_BUCKET)
            .name(ChatColor.YELLOW + "Hina")
            .lore(" ",
                    ChatColor.YELLOW + "" + ChatColor.BOLD + " Hina " + ChatColor.GRAY + "peut transformer la " + ChatColor.BLUE + ChatColor.BOLD + "Météo " + ChatColor.GRAY + "à sa guise.    ", " ")
            .build();

    @Override
    public String getName() {
        return "Hina";
    }

    @Override
    public String getIdentifier() {
        return "hina";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent meteo = new TextComponent(ChatColor.GRAY + "Prêtresse du temps: " + ChatColor.WHITE + "Vous êtes maudit. La " + ChatColor.BLUE + ChatColor.BOLD + "pluie" + ChatColor.WHITE + " vous suit. Sa position s'actualise " + ChatColor.BOLD + "toutes les 30 secondes" + ChatColor.WHITE + " et possède un rayon de " + ChatColor.YELLOW + "30x30" + ChatColor.WHITE + " autour de vous. Vous êtes le seul à pouvoir voir la limite du rayon. En dehors du rayon, il s'agira de la " + ChatColor.BLUE + "météo " + ChatColor.WHITE + "de base.");
        HoverHolder aMeteo = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + ChatColor.BOLD + "Avantages Météo" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.BLUE + ChatColor.BOLD + "Avantages Météo" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "En fonction de la " + ChatColor.BLUE + "météo " + ChatColor.BOLD + "invoquée" + ChatColor.WHITE + ", vous gagnez des " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + ".\n" +
                        ChatColor.BLUE + ChatColor.BOLD + "    " + WeatherType.RAINING.getName() + ChatColor.GRAY + "   ²» " + StringUtils.SPEED_SIGN + ChatColor.YELLOW + " +20% de Vitesse " + ChatColor.WHITE + "ainsi qu'un " + ChatColor.GOLD + ChatColor.BOLD + "double jump " + ChatColor.WHITE + "disponible toutes les 20 secondes.\n" +
                        ChatColor.GOLD + ChatColor.BOLD + "    " + WeatherType.SUNNY.getName() + ChatColor.GRAY + " » " + ChatColor.WHITE + "Une " + ChatColor.LIGHT_PURPLE + "régénération" + ChatColor.WHITE + " à hauteur de " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + " toutes les 5 secondes" + ChatColor.WHITE + ",\n" +
                        ChatColor.WHITE + "                          ainsi qu'un effet de " + ChatColor.GOLD + ChatColor.BOLD + "fire aspect" + ChatColor.WHITE + ChatColor.BOLD + " tout les 3 coups" + ChatColor.WHITE + ".\n"

        );


        DescriptionBuilder description = new DescriptionBuilder(this).passifs(meteo, aMeteo.toComponent()).activables();

        return description.build();
    }
}
