package be.artex.rolesffa.listeners.player;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.lame.LameType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityHitEntity implements Listener {
    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                int effectAmplifier = 0;

                for (PotionEffect effect : player.getActivePotionEffects()) {
                    if (effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
                        effectAmplifier = effect.getAmplifier();
                        break;
                    }
                }

                double percentage = 1.0 + (effectAmplifier * 1.3);

                damage = damage / (1.87 + (percentage / 100));
                damage = (damage / 10) * RoleUtils.getPlayerRole(player.getUniqueId()).getStrength();
            }

            if (Lame.getPlayerLame(player.getUniqueId()) != null && Lame.getPlayerLame(player.getUniqueId()).equals(LameType.STRENGTH))
                damage = (damage / 100) * 105;

        }

        Main.instance.getLogger().info("Nerfed damage: " + event.getDamage() + " to " + damage + ".");

        event.setDamage(damage);
    }
}
