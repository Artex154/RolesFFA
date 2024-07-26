package be.artex.rolesffa.api.roles;

import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.ImmenseSpeed;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.api.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Sanemi extends Role {

    @Override
    public String getName() {
        return ChatColor.GREEN + "Sanemi";
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(StringUtils.line);
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Rôle: " + getName());
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez " + ChatColor.AQUA + "Vitesse I " + ChatColor.GRAY + "de façon permanente.");
        description.addExtra("\n");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez une ");
        description.addExtra(new Lame().getDescription());
        description.addExtra(ChatColor.GRAY + ".");
        description.addExtra("\n " + ChatColor.DARK_GRAY + StringUtils.dot + " " + ChatColor.GRAY + "Vous possédez l'");
        description.addExtra(new ImmenseSpeed().getDescription());
        description.addExtra(ChatColor.GRAY + ".");
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
        RoleUtils.baseSetup(player, this);
        player.getInventory().addItem(Stacks.IMMENSE_SPEED);
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
    }
}
