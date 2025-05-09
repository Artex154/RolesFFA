package be.artex.rolesffa.role.technique.kyojuro;

import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class Purgatoire extends SPItem {
    public static HashMap<UUID, Integer> purgatoireLevel = new HashMap<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.PURGATOIRE;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.createItemDescription(getItemStack(), "le");
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("purgatoire");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.GRAY + " Le " + ChatColor.GOLD + "Purgatoire" + ChatColor.GRAY + " a été déjà été utilisé, faites un kill pour le réutiliser.");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45*20, 1));
        player.setMaxHealth(player.getMaxHealth() - 4);

        purgatoireLevel.put(uuid, 3);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0)
                player.setMaxHealth(player.getMaxHealth() + 4);
        }, 90*20);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0) {
                purgatoireLevel.put(uuid, 2);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous n'enlevez plus de " + ChatColor.LIGHT_PURPLE + "demis-coeurs" + ChatColor.GRAY + ".");
            }

        }, 10*20);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0) {
                purgatoireLevel.put(uuid, 1);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous ne mettez plus les joueurs en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ".");
            }
        }, 45*20);

        cooldown.addPlayer(uuid, Integer.MAX_VALUE);
    }
}
