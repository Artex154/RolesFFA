package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.api.SPItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }

        for (SPItem spItem : SPItemUtils.registeredSPitems) {
            if (event.getItem().equals(spItem.getItemStack())) {
                spItem.onClick(event);
            }
        }
    }
}
