package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.itemStacks.items.PreItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().getInventory().setItem(4, PreItems.SLECTION_BOOK.getStack());
    }
}
