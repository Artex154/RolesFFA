package be.artex.rolesffa.role.technique.kyojuro;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.item.lame.Lame;
import be.raft.crafty.item.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Kyojuro extends Role {
    public static final ArrayList<UUID> playerHalfHearts = new ArrayList<>();

    @Override
    public String getName() {
        return ChatColor.YELLOW + "Kyojuro";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .effect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0))
                .item(new Purgatoire(), new SouffleFeu(),new Lame())
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KYOJURO;
    }

    @Override
    public Team getCamp() {
        return Team.TECHNIQUE;
    }

    @Override
    public int getPlacement() {
        return 14;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));

        player.getInventory().setItem(7, Stacks.SOUFFLE);
        player.getInventory().addItem(Stacks.PURGATOIRE);
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
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
