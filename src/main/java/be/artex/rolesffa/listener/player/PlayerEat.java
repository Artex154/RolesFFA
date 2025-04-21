package be.artex.rolesffa.listener.player;

import be.artex.rolesffa.role.technique.shoto.Ice;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerEat implements Listener {
    @EventHandler
    public static void onPlayerEat(PlayerItemConsumeEvent event) {
        if (!event.getItem().getType().equals(Material.GOLDEN_APPLE))
            return;

        if (Ice.playersInIce.contains(event.getPlayer().getUniqueId()))
            event.setCancelled(true);
    }
}
