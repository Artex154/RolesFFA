package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class EntityHitEntity implements Listener {
    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
                damage = damage / 1.95;
        }


        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
                damage = (damage / 70) * 84;
        }

        Main.instance.getLogger().info("Nerfed damage: " + event.getDamage() + " to " + damage + ".");

        event.setDamage(damage);
    }
}
