package be.artex.rolesffa.api.roles;

import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.util.Stacks;
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
    public String getDescription() {
        return "";
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
        Role.Utils.baseSetup(player);
    }
}
