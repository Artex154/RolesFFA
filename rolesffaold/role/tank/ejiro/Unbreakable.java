package be.artex.rolesffaold.role.tank.ejiro;

import be.artex.rolesffaold.Main;
import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.Cooldown;
import be.artex.rolesffaold.api.item.SPItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

public class Unbreakable extends SPItem {
    public static ArrayList<UUID> playerWithResistance = new ArrayList<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.UNBREAKABLE;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("unbreakable");

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Unbreakable est en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(uuid) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*7, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*7, 0));

        playerWithResistance.add(player.getUniqueId());


        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (playerWithResistance.contains(player.getUniqueId()))
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));

            playerWithResistance.remove(player.getUniqueId());
        }, 21*7);

        cooldown.addPlayer(uuid, 80*20);
    }
}
