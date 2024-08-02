package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.cooldown.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        Role role = RoleUtils.getPlayerRole(player.getUniqueId());

        Cooldown.removePlayerFromAllCooldowns(player.getUniqueId());

        event.getDrops().clear();

        if (role == null) {
            Main.instance.getLogger().warning(player.getUniqueId().toString() + " (" + player.getName() + ") died with no role");
            event.setDeathMessage("");

            return;
        }

        if (killer == null) {
            event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " est mort.\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

            return;
        }

        for (ItemStack itemStack : event.getDrops()) {
            if (itemStack.getType().equals(Material.GOLDEN_APPLE))
                killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
        }

        event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été assassiné par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + ".\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

        RoleUtils.setPlayerRole(player.getUniqueId(), null);
        Lame.setPlayerLame(player.getUniqueId(), null);
    }
}
