package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.api.role.Role;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlight implements Listener {
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Role role = Role.getPlayerRole(event.getPlayer());

        if (role != null)
            role.onFlightToggle(event);
    }
}
