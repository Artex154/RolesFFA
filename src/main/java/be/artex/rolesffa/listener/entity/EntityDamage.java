package be.artex.rolesffa.listener.entity;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
   @EventHandler
   public void onEntityDamageEvent(EntityDamageEvent event) {
      if (event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getEntity() instanceof ArmorStand)
         event.setCancelled(true);
   }
}
