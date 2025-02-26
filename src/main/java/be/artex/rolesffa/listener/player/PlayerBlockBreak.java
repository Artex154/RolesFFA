package be.artex.rolesffa.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockBreak implements Listener {
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        switch (event.getBlock().getType()) {
            case STONE:
            case ICE:
                event.setCancelled(true);
        }
    }
}
