package be.artex.rolesffa.api;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class SPItem {
    public abstract ItemStack getItemStack();
    public abstract TextComponent getDescription();



    public abstract void onClick(PlayerInteractEvent event);
}
