package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.Strength;
import be.artex.rolesffa.util.WorldUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        if (!WorldUtils.isWorldInitialized)
            WorldUtils.initializeWorld();

        if (!WorldUtils.isWorldBorderInitialized(Bukkit.getWorlds().get(0)))
            WorldUtils.initializeWorldBorder(Bukkit.getWorlds().get(0));

        if (!player.getGameMode().equals(GameMode.ADVENTURE))
            player.setGameMode(GameMode.ADVENTURE);

        playerInventory.clear();

        Lame.setPlayerLame(event.getPlayer().getUniqueId(), null);

        ItemStack air = new ItemStack(Material.AIR);

        ItemStack[] armorContent = {air, air, air, air};

        playerInventory.setArmorContents(armorContent);

        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }

        player.setMaxHealth(20);
        player.setHealth(20);

        player.setWalkSpeed(0.2f);

        playerInventory.setItem(4, Stacks.CHOOSE_BOOK);

        if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
            player.setGameMode(GameMode.ADVENTURE);
        }

        event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + "] " + player.getName());

        player.teleport(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));

        Strength.playerStrength.put(player.getUniqueId(), null);
    }
}
