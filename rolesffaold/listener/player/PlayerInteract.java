package be.artex.rolesffaold.listener.player;

import be.artex.rolesffaold.api.item.SPItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null)
            return;

        for (SPItem spItem : SPItem.registeredItems) {
            if (event.getItem().getItemMeta().equals(spItem.getItemStack().getItemMeta()))
                spItem.onClick(event);
        }
    }
}
