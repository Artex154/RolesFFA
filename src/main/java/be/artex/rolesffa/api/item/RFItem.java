package be.artex.rolesffa.api.item;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class RFItem {
    public abstract ItemStack getItem();

    public void onClick(PlayerInteractEvent event) {
    }
}
