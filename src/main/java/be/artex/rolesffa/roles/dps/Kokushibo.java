package be.artex.rolesffa.roles.dps;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.descriptionBuilder.DescriptionBuilder;
import be.artex.rolesffa.api.descriptionBuilder.HoverHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Kokushibo extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.DIAMOND_SWORD)
            .name(ChatColor.RED + "Kokushibo")
            .lore(" ",
                    ChatColor.RED + "" + ChatColor.BOLD + " Kokushibo " + ChatColor.GRAY +  "possède " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 105% de Force" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Il dispose aussi de sa " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.GRAY + ", et d'une " + ChatColor.RED + "progression" + ChatColor.GRAY + " de pouvoirs.", " ")
            .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    @Override
    public String getName() {
        return "Kokushibo";
    }

    @Override
    public String getIdentifier() {
        return "kokushibo";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.DPS;
    }

    @Override
    public TextComponent getDescription() {
        HoverHolder lame = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + ChatColor.BOLD + "Tout les " + ChatColor.RED + ChatColor.BOLD + "20 coups" + ChatColor.WHITE + ", vous infligez un " + ChatColor.RED + ChatColor.BOLD + "saignement" + ChatColor.WHITE + " de " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + "/7 secondes pendant 21 secondes" + ChatColor.WHITE + " qui vous est " + ChatColor.LIGHT_PURPLE + "régénéré" + ChatColor.WHITE + ".      \n "
        );

        HoverHolder evolution = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "Evolution" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.RED + ChatColor.BOLD + "Evolution" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Tout les " + ChatColor.RED + "x coups mis" + ChatColor.WHITE + ", vous gagnez des " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + ".       \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous gardez les " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + " des stades précédents.     \n \n" +
                        ChatColor.RED + ChatColor.BOLD + "    20 coups" + ChatColor.GRAY + " » " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " +5% de Force\n" +
                        ChatColor.RED + ChatColor.BOLD + "    50 coups" + ChatColor.GRAY + " » " + ChatColor.WHITE + "le " + ChatColor.RED + "saignement " + ChatColor.WHITE + "passe à " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + "/4 secondes pendant 16 secondes\n" +
                        ChatColor.RED + ChatColor.BOLD + "    75 coups" + ChatColor.GRAY + " » " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " +5% de Force\n" +
                        ChatColor.RED + ChatColor.BOLD + "    100 coups" + ChatColor.GRAY + " » " + ChatColor.WHITE + "le " + ChatColor.RED + ChatColor.BOLD + "saignement " + ChatColor.WHITE + "arrive tout les " + ChatColor.RED + ChatColor.BOLD + "13 coups " + ChatColor.WHITE + "au lieu de 20.\n"
        );

        DescriptionBuilder description = new DescriptionBuilder(this)
                .passifs(lame, evolution);

        return description.build();
    }

    @Override
    public int getBonusStrength() {
        return 5;
    }
}
