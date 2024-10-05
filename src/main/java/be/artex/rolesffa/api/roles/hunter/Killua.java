package be.artex.rolesffa.api.roles.hunter;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.api.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Killua extends Role {

    public static final HashMap<UUID, Integer> playerHitNumber = new HashMap<>();
    public static final ArrayList<UUID> playerWithSpeed = new ArrayList<>();

    @Override
    public String getName() {
        return ChatColor.GREEN + "Killua";
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("qsdzqsdqzds");
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.QUARTZ);
    }

    @Override
    public Team getCamp() {
        return Team.HUNTER5;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
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

        if (hitNumber != 9)
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
