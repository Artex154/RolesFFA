package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.registry.RolesRegistries;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        ItemStack stack = event.getItem();

        if (stack == null || stack.getType() == Material.AIR)
            return;

        RolesRegistries.ITEMS.getItemFromStack(stack).ifPresent(item -> item.onClick(event));
    }
}
