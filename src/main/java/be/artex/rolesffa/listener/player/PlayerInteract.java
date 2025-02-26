package be.artex.rolesffa.listener.player;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.api.item.SPItemUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null)
            return;

        for (SPItem spItem : SPItemUtils.registeredItems) {
            if (event.getItem().getItemMeta().equals(spItem.getItemStack().getItemMeta()))
                spItem.onClick(event);
        }
    }
}
