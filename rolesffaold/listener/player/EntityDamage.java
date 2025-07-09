package be.artex.rolesffaold.listener.player;

import be.artex.rolesffaold.item.lame.Lame;
import be.artex.rolesffaold.item.lame.LameType;
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
        UUID playerUUID = player.getUniqueId();
        EntityDamageEvent.DamageCause cause = event.getCause();

        switch (cause) {
            case LIGHTNING:
                event.setCancelled(true);
                break;
            case FALL:
                checkLameVerte(playerUUID, event);
                break;
        }

    }

    private static void checkLameVerte (UUID uuid, EntityDamageEvent event) {
        LameType lame = Lame.getPlayerLame(uuid);

        if (lame == LameType.NOFALL)
            event.setCancelled(true);
    }
}
