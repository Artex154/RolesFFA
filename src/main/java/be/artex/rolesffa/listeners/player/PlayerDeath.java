package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.sabito.Dash;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.api.items.tomura.Mains;
import be.artex.rolesffa.api.roles.hunter.Killua;
import be.artex.rolesffa.api.roles.pirate.Mihawk;
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

import java.util.Objects;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();

        Role role = RoleUtils.getPlayerRole(player.getUniqueId());

        Cooldown.removePlayerFromAllCooldowns(player.getUniqueId());
        Cooldown.removePlayerFromAllCooldowns(killer.getUniqueId());

        int droppedGoldenApples = 0;
        int droppedArrow = 0;

        for (ItemStack itemStack : event.getDrops()) {
            switch (itemStack.getType()) {
                case GOLDEN_APPLE:
                    droppedGoldenApples += itemStack.getAmount();
                    break;

                case ARROW:
                    droppedArrow += itemStack.getAmount();
                    break;

                case BARRIER:
                    if (Mains.playerLosedItems.get(player.getUniqueId()) != null && Mains.playerLosedItems.get(player.getUniqueId()).getStack().getType() != null && Mains.playerLosedItems.get(player.getUniqueId()).getStack().getType().equals(Material.GOLDEN_APPLE))
                        droppedGoldenApples += Mains.playerLosedItems.get(player.getUniqueId()).getStack().getAmount();
                    break;
            }
        }

        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.GOLDEN_APPLE, droppedGoldenApples));
        event.getDrops().add(new ItemStack(Material.ARROW, droppedArrow));
        event.getDrops().add(new ItemStack(Material.LAVA_BUCKET));
        event.getDrops().add(new ItemStack(Material.WATER_BUCKET));
        event.getDrops().add(new ItemStack(Material.COBBLESTONE, 64));

        Dash.playerWithSpeed.remove(player.getUniqueId());
        Mihawk.playerWithoutResistant.remove(player.getUniqueId());
        Mains.playerLosedItems.put(player.getUniqueId(), null);

        Killua.playerHitNumber.put(player.getUniqueId(), 0);
        Killua.playerWithSpeed.remove(player.getUniqueId());

        Strength.playerStrength.put(player.getUniqueId(), null);

        if (role == null) {
            Main.instance.getLogger().warning(player.getUniqueId().toString() + " (" + player.getName() + ") died with no role");
            event.setDeathMessage("");

            return;
        }

        if (killer == null) {
            event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " est mort.\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

            return;
        }

        int killerGoldenApples = droppedGoldenApples;

        for (ItemStack itemStack : killer.getInventory()) {
            if (Objects.requireNonNull(itemStack.getType()) == Material.GOLDEN_APPLE)
                killerGoldenApples += itemStack.getAmount();
        }

        if (killerGoldenApples > 14)
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 14 - (killerGoldenApples - droppedGoldenApples)));

        event.setDeathMessage(StringUtils.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été assassiné par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + ".\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + StringUtils.line);

        RoleUtils.setPlayerRole(player.getUniqueId(), null);
        Lame.setPlayerLame(player.getUniqueId(), null);

        RoleUtils.getPlayerRole(killer.getUniqueId()).onPlayerKill(event);

        killer.setHealth(killer.getMaxHealth() / 2);
    }
}
