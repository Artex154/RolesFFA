package be.artex.rolesffa.role.technique.shoto;

import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ice extends SPItem {
    public static List<UUID> playersInIce = new ArrayList<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.ICE;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.getItemDescription(getItemStack(), "votre");
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        UUID damagerUUID = event.getDamager().getUniqueId();
        Location loc = player.getLocation();

        Cooldown cooldown = Cooldown.get("ice");

        if (cooldown.isPlayerInCooldown(damagerUUID)) {
            damager.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + " Votre Coté Gelé" + ChatColor.GRAY + " est en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(damagerUUID) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        playersInIce.add(player.getUniqueId());

        loc.setX(loc.getBlockX() + 0.5);
        loc.setY(loc.getBlockY());
        loc.setZ(loc.getBlockZ() + 0.5);

        player.teleport(loc);

        int radius = 1;

        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Location blockLoc = loc.clone().add(x, y, z);

                    if (x == 0 && y == 1 && z == 0) continue;

                    blockLoc.getBlock().setType(Material.ICE);
                }
            }
        }

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Location blockLoc = loc.clone().add(x, 2, z);
                blockLoc.getBlock().setType(Material.ICE);
            }
        }

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Location blockLoc = loc.clone().add(x, -1, z);
                blockLoc.getBlock().setType(Material.ICE);
            }
        }

        loc.getBlock().setType(Material.AIR);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () ->
           player.setVelocity(new Vector(0, 0, 0)), 1L);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            for (int x = -radius; x <= radius; x++) {
                for (int y = 0; y <= 2; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Location blockLoc = loc.clone().add(x, y, z);

                        if (x == 0 && y == 1 && z == 0) continue;

                        blockLoc.getBlock().setType(Material.AIR);
                    }
                }
            }

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    Location blockLoc = loc.clone().add(x, 2, z);
                    blockLoc.getBlock().setType(Material.AIR);
                }
            }

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    Location blockLoc = loc.clone().add(x, -1, z);
                    blockLoc.getBlock().setType(Material.AIR);
                }
            }

            playersInIce.remove(player.getUniqueId());
        }, 7*20L);

        cooldown.addPlayer(damagerUUID, 70*20L);
    }
}
