package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.lame.LameType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            return;

        UUID playerUUID = player.getUniqueId();

        if (Lame.getPlayerLame(playerUUID) != null && Lame.getPlayerLame(playerUUID).equals(LameType.NOFALL))
            event.setCancelled(true);
    }
}
