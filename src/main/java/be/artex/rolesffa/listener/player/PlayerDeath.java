package be.artex.rolesffa.listener.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.item.GoldenHead;
import be.artex.rolesffa.role.heros.ejiro.Unbreakable;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.role.slayer.sabito.Dash;
import be.artex.rolesffa.role.slayer.item.lame.Lame;
import be.artex.rolesffa.role.allianceSV.tomura.mains.Mains;
import be.artex.rolesffa.role.hunter.Killua;
import be.artex.rolesffa.listener.player.playerDamagePlayer.Strength;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.Cooldown;
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

        Dash.playerWithSpeed.remove(player.getUniqueId());
        Mains.playerLosedItems.put(player.getUniqueId(), null);
        Unbreakable.playerWithResistance.remove(player.getUniqueId());

        Killua.playerHitNumber.put(player.getUniqueId(), null);
        Killua.playerWithSpeed.remove(player.getUniqueId());

        Strength.playerStrength.put(player.getUniqueId(), null);

        GoldenHead.playerSpeed.put(player.getUniqueId(), null);

        RoleUtils.setPlayerRole(player.getUniqueId(), null);
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
                if (itemStack.getType() == Material.GOLDEN_APPLE && itemStack.getItemMeta() != Stacks.HEAD(1).getItemMeta())
                    killerGoldenApples += itemStack.getAmount();
            }
        }

        killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, (14 - killerGoldenApples)));

        if (role == null) {
            Main.instance.getLogger().warning(player.getUniqueId().toString() + " (" + player.getName() + ") died with no role");
            event.setDeathMessage("");

            return;
        }

        killer.getInventory().addItem(Stacks.HEAD(1));

        event.setDeathMessage(Main.line + "\n" + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " a été assassiné par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + ".\nSon rôle était: " + role.getName() + ChatColor.GRAY + ".\n" + Main.line);

        RoleUtils.getPlayerRole(killer.getUniqueId()).onPlayerKill(event);

        killer.setHealth(killer.getMaxHealth());
    }
}
