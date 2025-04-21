package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Killua extends Role {
    public static final HashMap<UUID, Integer> playerHitNumber = new HashMap<>();
    public static final ArrayList<UUID> playerWithSpeed = new ArrayList<>();

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .custom(ChatColor.GRAY + "Après votre " + ChatColor.AQUA + "dixième coup" + ChatColor.GRAY + ", vous faites apparaitre " + ChatColor.AQUA + "un éclaire" + ChatColor.GRAY + " qui fait " + ChatColor.RED + "1,5 coeurs de dégats" + ChatColor.GRAY + " et donne " + ChatColor.YELLOW + "vitesse 2" + ChatColor.GRAY + " pendant" + ChatColor.YELLOW + " 4 secondes" + ChatColor.GRAY + ".")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KILLUA;
    }

    @Override
    public RoleType getType() {
        return RoleType.DPS;
    }

    @Override
    public int getPlacement() {
        return 10;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }

    @Override
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        int hitNumber;

        if (playerHitNumber.get(damager.getUniqueId()) == null) {
            playerHitNumber.put(damager.getUniqueId(), 1);
            return;
        } else {
            hitNumber = playerHitNumber.get(damager.getUniqueId());
            hitNumber++;

            playerHitNumber.put(damager.getUniqueId(), hitNumber);
        }

        if (hitNumber != 10)
            return;

        playerHitNumber.put(damager.getUniqueId(), 0);

        damager.removePotionEffect(PotionEffectType.SPEED);

        damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 1));

        playerWithSpeed.add(damager.getUniqueId());

        event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());

        if ((player.getHealth() - 3) < 0)
            player.setHealth(1);
        else
            player.setHealth(player.getHealth() - 3);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (playerWithSpeed.contains(damager.getUniqueId())) {
                damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                playerWithSpeed.remove(damager.getUniqueId());
            }
        }, 81);

    }
}
