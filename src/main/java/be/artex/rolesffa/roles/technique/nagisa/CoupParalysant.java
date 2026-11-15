package be.artex.rolesffa.roles.technique.nagisa;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.stats.StatValues;
import be.artex.rolesffa.stats.Stats;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CoupParalysant extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.NETHER_STAR)
            .name(ChatColor.AQUA + "" + ChatColor.BOLD + "Coup Paralysant")
            .lore(" ",
                    ChatColor.GRAY + " En faisant un clique droit, vous " + ChatColor.AQUA + "paralyserez " + ChatColor.GRAY + "tous les " + ChatColor.AQUA + "joueurs      ", ChatColor.GRAY + " dans un" + ChatColor.AQUA + " rayon" + ChatColor.GRAY + " de" + ChatColor.AQUA + " 10 blocs" + ChatColor.GRAY + ", pendant " + ChatColor.YELLOW + "3 secondes" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " Les " + ChatColor.AQUA + "joueurs paralysés" + ChatColor.GRAY + " auront " + StringHelper.RESISTANCE_SIGN + ChatColor.GRAY + " -15% de resistance lors de la paralysie.      ",
                    " ", ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final Set<Player> FROZEN_PLAYERS = ConcurrentHashMap.newKeySet();
    public static final Set<Player> RESISTANCE_DEBUFF = ConcurrentHashMap.newKeySet();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.getCooldown("coup_paralysant", Duration.of(40, ChronoUnit.SECONDS), "Votre " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant");
        Player player = event.getPlayer();

        if (cooldown.isPlayerInCooldown(player)) {
            player.sendMessage(Message.cooldownTimeLeft(cooldown.getPlayerCooldownTimeLeft(player)));
            return;
        }

        player.sendMessage(Message.generate("Vous avez utilisé votre " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant" + ChatColor.GRAY + "."));

        cooldown.putPlayerInCooldown(player);

        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (!(entity instanceof Player))
                return;

            Player p = (Player) entity;

            stunPlayer(p, Duration.of(3, ChronoUnit.SECONDS));

            p.sendMessage(Message.generate("Vous subissez le " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant" + ChatColor.GRAY + " de " + ChatColor.YELLOW + "Nagisa" + ChatColor.GRAY + "."));

            RESISTANCE_DEBUFF.add(p);
            Stats stats = Stats.get(p.getUniqueId());
            stats.addBonus(StatValues.RESISTANCE, -15);

            Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
                if (!RESISTANCE_DEBUFF.contains(player))
                    return;

                RESISTANCE_DEBUFF.remove(p);
                stats.addBonus(StatValues.RESISTANCE, 15);
            }, 3*20L);
        }
    }

    public static void stunPlayer(@NotNull Player target, Duration duration) {
        ArmorStand h = (ArmorStand) target.getWorld().spawnEntity(target.getLocation().clone().subtract(0, 1, 0), EntityType.ARMOR_STAND);
        h.setPassenger(target);
        h.setVisible(false);
        h.setGravity(false);
        h.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 254, false, false));
        FROZEN_PLAYERS.add(target);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            h.setHealth(0);
            FROZEN_PLAYERS.remove(target);
        }, duration.getSeconds() * 20);

    }
}
