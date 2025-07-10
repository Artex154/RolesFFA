package be.artex.rolesffa.api.item;

import be.artex.rolesffa.itemStacks.ItemStacks;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class RFItems {
    public abstract ItemStacks getItem();

    public void onClick(PlayerInteractEvent event) {
    }
}
