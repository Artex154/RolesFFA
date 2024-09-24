package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.sabito.Dash;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.Strength;
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
        Cooldown.removePlayerFromAllCooldowns(killer.getUniqueId());

        int goldenApples = 0;
        int arrow = 0;

        for (ItemStack itemStack : event.getDrops()) {
            switch (itemStack.getType()) {
                case GOLDEN_APPLE:
                    goldenApples += itemStack.getAmount();
                    break;

                case ARROW:
                    arrow += itemStack.getAmount();
                    break;
            }
        }

        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.GOLDEN_APPLE, goldenApples));
        event.getDrops().add(new ItemStack(Material.ARROW, arrow));
        event.getDrops().add(new ItemStack(Material.LAVA_BUCKET));
        event.getDrops().add(new ItemStack(Material.WATER_BUCKET));
        event.getDrops().add(new ItemStack(Material.COBBLESTONE, 64));

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

        RoleUtils.getPlayerRole(killer.getUniqueId()).onPlayerKill(event);

        killer.setHealth(killer.getMaxHealth());

        Dash.playerWithSpeed.remove(player.getUniqueId());

        Strength.playerStrength.put(player.getUniqueId(), null);
    }
}
