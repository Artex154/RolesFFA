package be.artex.rolesffa.api;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface SPItem {
    ItemStack getItemStack();
    void onClick(PlayerInteractEvent event);

    class Utils {
        public static ArrayList<SPItem> registeredSPitems = new ArrayList<>();
    }
}
