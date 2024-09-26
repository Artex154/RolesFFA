package be.artex.rolesffa.api.roles.pirate;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Strength;
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
import java.util.UUID;

public class Mihawk extends Role {

    public static ArrayList<UUID> playerWithoutResistant = new ArrayList<>();

    @Override
    public String getName() {
        return ChatColor.GREEN + "Mihawk";
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("qsdqz");
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.DIAMOND_SWORD);
    }

    @Override
    public Team getCamp() {
        return Team.PIRATES;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        Strength.playerStrength.put(player.getUniqueId(), 13f);
    }

    @Override
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        if (!player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
            return;

        int ticksLeft = 0;
        int amplifier = 0;

        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            if (potionEffect.getType().equals(PotionEffectType.DAMAGE_RESISTANCE)) {
                ticksLeft = potionEffect.getDuration();
                amplifier = potionEffect.getAmplifier();
                break;
            }
        }

        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

        playerWithoutResistant.add(player.getUniqueId());

        damager.sendMessage((ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez enlever " + ChatColor.BOLD + "resistance" + ChatColor.AQUA + " pendant " + ChatColor.BOLD + "2 secondes " + ChatColor.AQUA + "à votre adversaire."));

        int finalTicksLeft = ticksLeft;
        int finalAmplifier = amplifier;

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (playerWithoutResistant.contains(player.getUniqueId())) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, finalTicksLeft, finalAmplifier));
                playerWithoutResistant.remove(player.getUniqueId());
            }
        }, 40);
    }
}
