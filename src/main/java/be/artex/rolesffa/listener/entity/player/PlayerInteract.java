package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.api.item.RFItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        ItemStack stack = event.getItem();
        RFItems item = RFItems.getItemFromStack(stack);

        if (item == null)
            return;

        item.onClick(event);
    }
}
