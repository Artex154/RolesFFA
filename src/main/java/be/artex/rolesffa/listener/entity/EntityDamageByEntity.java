package be.artex.rolesffa.listener.entity;

import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.stats.Resistance;
import be.artex.rolesffa.stats.Strength;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        double damage = event.getDamage();

        double strengthPercent   = Strength.getPlayerStrength(damager);
        double resistancePercent = Resistance.getPlayerResistance(player);

        double netPercent = (strengthPercent - resistancePercent) / 100;

        double finalDamage = damage * (1 + netPercent);

        event.setDamage(finalDamage);

        RolesRegistries.ITEMS.getItemFromStack(damager.getItemInHand()).ifPresent(item -> item.onHit(event));
    }
}
