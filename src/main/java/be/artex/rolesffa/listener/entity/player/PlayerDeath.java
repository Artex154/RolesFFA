package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.helper.StringHelper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Role playerRole = RoleUtils.getPlayerRole(player);

        if (playerRole == null)
            return;

        if (player.getKiller() == null) {
            event.setDeathMessage(StringHelper.LINE + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " est mort.\nSon rôle était " + playerRole.getType().getColor() + playerRole.getName() + ChatColor.GRAY + ".\n" + StringHelper.LINE);
            return;
        }

        Player killer = player.getKiller();

        event.setDeathMessage(StringHelper.LINE + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été tué par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + ".\nSon rôle était " + playerRole.getType().getColor() + playerRole.getName() + ChatColor.GRAY + ".\n" + StringHelper.LINE);

        Role killerRole = RoleUtils.getPlayerRole(killer);

        if (killerRole == null)
            return;

        killerRole.onPlayerKill(killer, player);
    }
}
