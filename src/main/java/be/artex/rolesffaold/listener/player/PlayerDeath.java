package be.artex.rolesffaold.listener.player;

import be.artex.rolesffaold.Main;
import be.artex.rolesffaold.role.tank.ejiro.Unbreakable;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.role.technique.kyojuro.Kyojuro;
import be.artex.rolesffaold.role.technique.kyojuro.Purgatoire;
import be.artex.rolesffaold.item.lame.Lame;
import be.artex.rolesffaold.role.technique.shoto.Ice;
import be.artex.rolesffaold.role.technique.tomura.mains.Mains;
import be.artex.rolesffaold.role.DPS.Killua;
import be.artex.rolesffaold.listener.player.playerDamagePlayer.Strength;
import be.artex.rolesffaold.Cooldown;
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

        Role role = Role.getPlayerRole(player.getUniqueId());

        Cooldown.removePlayerFromAllCooldowns(player.getUniqueId());
        Cooldown.removePlayerFromAllCooldowns(killer.getUniqueId());

        Mains.playerLosedItems.put(player.getUniqueId(), null);
        Unbreakable.playerWithResistance.remove(player.getUniqueId());
        Ice.playersInIce.remove(player.getUniqueId());

        Killua.playerHitNumber.put(player.getUniqueId(), null);
        Killua.playerWithSpeed.remove(player.getUniqueId());

        Strength.playerStrength.put(player.getUniqueId(), null);

        Kyojuro.playerHalfHearts.remove(player.getUniqueId());
        Purgatoire.purgatoireLevel.put(player.getUniqueId(), 0);

        Lame.setPlayerLame(player.getUniqueId(), null);

        int droppedArrow = 0;

        for (ItemStack itemStack : event.getDrops()) {
            if (itemStack.getType() == Material.ARROW)
                droppedArrow += itemStack.getAmount();
        }

        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.ARROW, droppedArrow));
        event.getDrops().add(new ItemStack(Material.LAVA_BUCKET));
        event.getDrops().add(new ItemStack(Material.WATER_BUCKET));
        event.getDrops().add(new ItemStack(Material.COBBLESTONE, 64));

        int killerGoldenApples = 0;

        for (ItemStack itemStack : killer.getInventory()) {
            if (itemStack != null && itemStack.getType() != null) {
                if (itemStack.getType() == Material.GOLDEN_APPLE)
                    killerGoldenApples += itemStack.getAmount();
            }
        }

        killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, (16 - killerGoldenApples)));

        if (role == null) {
            Main.instance.getLogger().warning(player.getUniqueId().toString() + " (" + player.getName() + ") died with no role");
            event.setDeathMessage("");

            return;
        }

        event.setDeathMessage(Main.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été assassiné par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + "(" + Role.getPlayerRole(killer.getUniqueId()).getName() + ChatColor.GRAY + ").\nSon rôle était " + role.getName() + ChatColor.GRAY + ".\n" + Main.line);

        Role.getPlayerRole(killer.getUniqueId()).onPlayerKill(event);

        killer.setHealth(killer.getMaxHealth());
    }
}
