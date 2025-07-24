package be.artex.rolesffa.api.item;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RFItem {
    public abstract ItemStack getItem();

    public void onClick(PlayerInteractEvent event) {
    }
}
