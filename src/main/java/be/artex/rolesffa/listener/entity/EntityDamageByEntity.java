package be.artex.rolesffa.listener.entity;

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

        double strengthBuff = ((damage / 100) * Strength.getPlayerStrength(damager)) - damage;
        System.out.println("strength buff : " + strengthBuff);

        double resistanceBuff = ((damage * 100) / Resistance.getPlayerResistance(player)) - damage;
        System.out.println("resistance buff : " + resistanceBuff);

        damage = strengthBuff - resistanceBuff;
        System.out.println("final damage : " + damage);

        event.setDamage(damage);
    }
}
