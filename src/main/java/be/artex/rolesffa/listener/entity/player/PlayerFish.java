package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.registry.RolesRegistries;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFish implements Listener {
    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        ItemStack stack = event.getPlayer().getItemInHand();

        if (stack == null || stack.getType() != Material.FISHING_ROD)
            return;

        RolesRegistries.ITEMS.getItemFromStack(stack).ifPresent(item -> item.onFish(event));
    }
}
