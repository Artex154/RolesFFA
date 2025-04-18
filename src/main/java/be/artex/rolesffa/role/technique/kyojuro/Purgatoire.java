package be.artex.rolesffa.role.technique.kyojuro;

import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class Purgatoire extends SPItem {
    public static HashMap<UUID, Integer> purgatoireLevel = new HashMap<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.PURGATOIRE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(ChatColor.GRAY + "le " + getItemStack().getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(getItemStack().getItemMeta().getDisplayName() + "\n\n" +
                Main.dot + ChatColor.GRAY + "En faissant un clique droit, vous gagnerez " + ChatColor.YELLOW + "Speed II" + ChatColor.GRAY + ". \n" +
                Main.dot + ChatColor.GRAY + "Quand vous infligerez un " + ChatColor.RED + "coup" + ChatColor.GRAY + " sur un joueur, vous le metterez en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ".      \n" +
                Main.dot + ChatColor.GRAY + "Ces pouvoirs durent" + ChatColor.YELLOW + " 45 secondes" + ChatColor.GRAY + ".\n\n" +
                Main.dot + ChatColor.GRAY + "En plus de mettre le joueur en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ", tout vos prochains coups dans les dix secondes qui suivent,    \n" +
                ChatColor.GRAY + "   vous enlèverez " + ChatColor.LIGHT_PURPLE + "1 demi-coeur " + ChatColor.GRAY + "permanent au joueur tappé.\n" +
                Main.dot + ChatColor.GRAY + "Les coeurs sont redonnés après " + ChatColor.YELLOW + "1 minute" + ChatColor.GRAY + ".\n\n" +
                Main.dot + ChatColor.GRAY + "En contre partie, vous perdrez" + ChatColor.LIGHT_PURPLE  + " 2 coeurs " + ChatColor.GRAY + "permanent pendant " + ChatColor.YELLOW + "1 minute 30" + ChatColor.GRAY + ".\n\n" +
                Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 1x/kill" + ChatColor.GRAY + ".\n")});

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("purgatoire");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Votre " + ChatColor.BOLD + "Purgatoire" + ChatColor.AQUA + " a été déjà été utilisé, faites un kill pour le réutiliser.");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 45*20, 1));
        player.setMaxHealth(player.getMaxHealth() - 4);

        purgatoireLevel.put(uuid, 3);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0)
                player.setMaxHealth(player.getMaxHealth() + 4);
        }, 90*20);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0) {
                purgatoireLevel.put(uuid, 2);
                player.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "RolesFFA" + ChatColor.RED + "]" + ChatColor.GRAY + " Vous n'enlevez plus de " + ChatColor.LIGHT_PURPLE + "demis-coeurs" + ChatColor.GRAY + ".");
            }

        }, 10*20);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (purgatoireLevel.get(uuid) != 0) {
                purgatoireLevel.put(uuid, 1);
                player.sendMessage(ChatColor.RED + "[" + ChatColor.YELLOW + "RolesFFA" + ChatColor.RED + "]" + ChatColor.GRAY + " Vous ne mettez plus les joueurs en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ".");
            }

        }, 45*20);

        cooldown.addPlayer(uuid, Integer.MAX_VALUE);
    }
}
