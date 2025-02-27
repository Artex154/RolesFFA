package be.artex.rolesffa.listener.player;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockPlace implements Listener {
    @EventHandler
    public static void onPlayerBlockPlace(BlockPlaceEvent event) {
        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            event.getBlock().setType(Material.AIR);
        }, 10*20L);
    }
}
