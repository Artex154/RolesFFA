package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.util.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));
        event.getPlayer().getInventory().setItem(4, Stacks.CHOOSE_BOOK);
        event.getPlayer().setMaxHealth(20);
    }
}
