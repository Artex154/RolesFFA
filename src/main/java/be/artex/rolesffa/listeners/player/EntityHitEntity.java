package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.Strength;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.lame.LameType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityHitEntity implements Listener {
    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            RoleUtils.getPlayerRole(player.getUniqueId()).onPlayerHit(event);

            if (!player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
                return;

            int effectAmplifier = 0;

            for (PotionEffect effect : player.getActivePotionEffects()) {
                if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
                    effectAmplifier = effect.getAmplifier();
                    break;
                }
            }

            double percentage = 1.0 + (effectAmplifier * 1.3);

            damage = damage / (1.87 + (percentage / 100));

            if (Strength.playerStrength.get(player.getUniqueId()) != null)
                damage = (damage / 10) * Strength.playerStrength.get(player.getUniqueId());

            if (Lame.getPlayerLame(player.getUniqueId()) != null && Lame.getPlayerLame(player.getUniqueId()).equals(LameType.STRENGTH))
                damage = (damage / 100) * 105;

        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (Lame.getPlayerLame(player.getUniqueId()) != null && Lame.getPlayerLame(player.getUniqueId()).equals(LameType.RESISTANCE))
                damage = (damage * 100) / 100;
        }

        event.setDamage(damage);
    }
}
