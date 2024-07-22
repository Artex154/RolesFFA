package be.artex.rolesffa.api.roles;

import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sanemi implements Role {
    @Override
    public String getName() {
        return ChatColor.GREEN + "Sanemi";
    }

    // TODO: add a description
    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(StringUtils.line);
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Rôle: " + getName());
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez " + ChatColor.AQUA + "Vitesse I " + ChatColor.GRAY + "de façon permanente.");
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".");
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez l'" + ChatColor.AQUA + "Immense Speed " + ChatColor.GRAY + "qui vous permet de faire un " + ChatColor.AQUA + "dash"  + ChatColor.GRAY + " en avant.");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous écoperez de " + ChatColor.GREEN + "NoFall" + ChatColor.GRAY + " pendant " + ChatColor.YELLOW + "15 secondes " + ChatColor.GRAY + "après votre " + ChatColor.AQUA + " dash" + ChatColor.GRAY + ".");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Cooldown: " + ChatColor.YELLOW + "70 secondes" + ChatColor.GRAY + ".");
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Inspiration: " + ChatColor.YELLOW + "Nakime Party + Artex54");
        description.addExtra(StringUtils.line);

        return description;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SANEMI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public Team getType() {
        return Team.DPS;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        Role.Utils.baseSetup(player, this);
    }
}
