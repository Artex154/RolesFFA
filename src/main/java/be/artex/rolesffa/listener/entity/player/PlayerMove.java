package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (CoupParalysant.FROZEN_PLAYERS.contains(event.getPlayer()))
            return;

        event.setCancelled(true);
    }
}
