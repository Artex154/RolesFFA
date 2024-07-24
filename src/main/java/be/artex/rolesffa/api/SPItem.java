package be.artex.rolesffa.api;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface SPItem {
    ItemStack getItemStack();
    TextComponent getDescription();

    void onClick(PlayerInteractEvent event);
}
