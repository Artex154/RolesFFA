package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
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
        Role playerRole = RoleUtils.getPlayerRole(player);

        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.LAVA_BUCKET));
        event.getDrops().add(new ItemStack(Material.WATER_BUCKET));
        event.getDrops().add(new ItemStack(Material.LEAVES, 64));
        event.getDrops().add(new ItemStack(Material.ARROW, 24));

        CoupParalysant.RESISTANCE_DEBUFF.remove(player);
        CoupParalysant.FROZEN_PLAYERS.remove(player);

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

        int killerGoldenApples = 0;

        for (ItemStack itemStack : killer.getInventory()) {
            if (itemStack != null && itemStack.getType() != null) {
                if (itemStack.getType().equals(Material.GOLDEN_APPLE))
                    killerGoldenApples += itemStack.getAmount();
            }
        }
        
        RoleUtils.armorSetup(killer.getInventory());

        event.getDrops().add(new ItemStack(Material.GOLDEN_APPLE, (14 - killerGoldenApples)));

        killer.setHealth(killer.getMaxHealth());
    }
}
