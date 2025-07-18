package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.itemStacks.items.PreItems;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class PlayerJoin implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(null);

        inventory.setItem(4, PreItems.SLECTION_BOOK.getStack());

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));

        CoupParalysant.FROZEN_PLAYERS.remove(player);
    }
}
