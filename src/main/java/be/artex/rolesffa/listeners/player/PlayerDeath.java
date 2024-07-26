package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.api.RoleUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        Role role = RoleUtils.getPlayerRole(player.getUniqueId());

        if (role == null) {
            Main.instance.getLogger().warning(player.getUniqueId().toString() + " (" + player.getName() + ") died with no role");
            event.setDeathMessage("");

            return;
        }

        if (killer == null) {
            event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " est mort.\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

            return;
        }

        event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été assassiné par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + ".\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

        RoleUtils.setPlayerRole(player.getUniqueId(), null);
        Lame.setPlayerLame(player.getUniqueId(), null);
    }
}
