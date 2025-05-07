package be.artex.rolesffa.role.technique.kyojuro;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.item.lame.Lame;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Kyojuro extends Role {
    public static final ArrayList<UUID> playerHalfHearts = new ArrayList<>();

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KYOJURO;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
    }

    @Override
    public List<ItemHolder> getItems() {
        return Arrays.asList(new ItemHolder(new SouffleFeu(), 8), new ItemHolder(new Purgatoire()), new ItemHolder(new Lame()));
    }

    @Override
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        HashMap<UUID, Integer> purgatoire = Purgatoire.purgatoireLevel;

        switch (purgatoire.get(damager.getUniqueId())) {
            case 2:
                setFire(player);
            case 3:
                setFire(player);
                removeHalfAHeart(player, damager);
        }
    }

    private static void setFire(Player p) {
        p.setFireTicks(10*20);
    }

    private static void removeHalfAHeart(Player player, Player damager) {
        playerHalfHearts.add(player.getUniqueId());

        player.setMaxHealth(player.getMaxHealth() - 1);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (!playerHalfHearts.contains(player.getUniqueId()))
                return;

            player.setMaxHealth(player.getMaxHealth() + 1);
            damager.sendMessage(ChatColor.RED + "[" + ChatColor.GOLD + "RolesFFA" + ChatColor.RED + "]" + ChatColor.GRAY + " " + player.getName() + " a récupéré " + ChatColor.LIGHT_PURPLE + "1 demi-coeur" + ChatColor.GRAY + ".");
        }, 60*20);
    }


}
