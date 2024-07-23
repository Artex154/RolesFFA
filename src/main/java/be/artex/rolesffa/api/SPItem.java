package be.artex.rolesffa.api;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface SPItem {
    ItemStack getItemStack();
    TextComponent getDescription();

    void onClick(PlayerInteractEvent event);

    class Utils {
        public static ArrayList<SPItem> registeredSPitems = new ArrayList<>();
    }
}
