package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerDismount implements Listener {
    @EventHandler
    public void onPlayerToggleSneak(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        if (CoupParalysant.FROZEN_PLAYERS.contains(player))
            Bukkit.getScheduler().runTaskLater(Main.instance, () -> event.getDismounted().setPassenger(player), 1);
    }
}