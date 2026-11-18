package be.artex.rolesffa.listener.entity;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.api.stats.Effects;
import org.bukkit.GameMode;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof FishHook) {
            event.setCancelled(true);
            return;
        }

        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if (player.getGameMode() == GameMode.ADVENTURE || damager.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
            return;
        }

        Role playerRole = Role.getPlayerRole(player);
        Role damagerRole = Role.getPlayerRole(damager);

        double playerResistanceBonus = Effects.get(player.getUniqueId()).getBonus(RFEffectsType.RESISTANCE);
        double damagerStrengthBonus = Effects.get(damager.getUniqueId()).getBonus(RFEffectsType.STRENGTH);

        double multiplier = 1 + (damagerStrengthBonus - playerResistanceBonus) / 100;
        double finalDamage = event.getDamage() * multiplier;

        event.setDamage(finalDamage);

        RolesRegistries.ITEMS.getItemFromStack(damager.getItemInHand()).ifPresent(item -> item.onHit(event));
    }
}
