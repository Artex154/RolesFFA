package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        event.setRespawnLocation(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));
        player.getInventory().setItem(4, Stacks.CHOOSE_BOOK);
        player.setMaxHealth(20);
        player.setWalkSpeed(0.2f);
    }

}
