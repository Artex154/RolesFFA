package be.artex.rolesffa.item;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class GoldenHead extends SPItem {

    public static HashMap<UUID, Float> playerSpeed = new HashMap<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.HEAD(1);
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("Il n'y en a pas !");
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("golden_head");
        Player player = event.getPlayer();

        if (cooldown.isPlayerInCooldown(player.getUniqueId())) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Les " + ChatColor.BOLD + "Golden Head's" + ChatColor.AQUA + " sont en cooldown pour encore " + ChatColor.BOLD + cooldown.getTimeLeft(player.getUniqueId()) + ChatColor.AQUA + ".");
            return;
        }

        playerSpeed.put(player.getUniqueId(), player.getWalkSpeed());

        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.ABSORPTION);

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*10, 1));
        player.setWalkSpeed(player.getWalkSpeed() + ((player.getWalkSpeed() / 10) * 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 120*7, 1));

        int goldenHeads = 0;

        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && stack.getType() == Material.SKULL_ITEM) {
                goldenHeads += stack.getAmount();
            }
        }

        player.getInventory().remove(Material.SKULL_ITEM);

        goldenHeads--;

        player.getInventory().addItem(Stacks.HEAD(goldenHeads));

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (playerSpeed.get(player.getUniqueId()) != null)
                player.setWalkSpeed(playerSpeed.get(player.getUniqueId()));

        }, 10*20L);

        cooldown.addPlayer(player.getUniqueId(), 7);
    }

}
