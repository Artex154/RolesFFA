package be.artex.rolesffaold.item.lame;

import be.artex.rolesffaold.api.item.SPItem;
import be.artex.rolesffaold.Stacks;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Lame extends SPItem {
    public static HashMap<UUID, LameType> playerLames = new HashMap<>();

    public static void setPlayerLame(UUID uuid, LameType type) {
        playerLames.put(uuid, type);
    }

    public static LameType getPlayerLame(UUID uuid) {
        return playerLames.get(uuid);
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.LAME_DE_NICHIRINE;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.createItemDescription(getItemStack(), "une");
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        switch (new Random().nextInt(5)) {
            case 0:
                setPlayerLame(playerUUID, LameType.STRENGTH);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_GRAY + ChatColor.BOLD + "noire" + ChatColor.GRAY + ". \n" + ChatColor.GRAY + "(" + ChatColor.DARK_GRAY + ChatColor.BOLD + "+5%" + ChatColor.GRAY + " de" + ChatColor.DARK_GRAY + ChatColor.BOLD + " force" + ChatColor.GRAY + ")");
                break;
            case 1:
                setPlayerLame(playerUUID, LameType.RESISTANCE);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous avez reçu la lame de nichirine " + ChatColor.BOLD + "grise" + ChatColor.GRAY + ". \n" + ChatColor.GRAY + "(" + ChatColor.BOLD + "+5%" + ChatColor.GRAY + " de" + ChatColor.BOLD + " resistance" + ChatColor.GRAY + ")");
                break;
            case 2:
                setPlayerLame(playerUUID, LameType.LIFE);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous avez reçu la lame de nichirine " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "rose" + ChatColor.GRAY + ". \n" + ChatColor.GRAY + "(" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "+2 coeurs" + ChatColor.GRAY + ")");
                player.setMaxHealth(player.getMaxHealth() + 4);
                break;
            case 3:
                setPlayerLame(playerUUID, LameType.SPEED);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous avez reçu la lame de nichirine " + ChatColor.YELLOW + ChatColor.BOLD + "jaune" + ChatColor.GRAY + ". \n" + ChatColor.GRAY + "(" + ChatColor.YELLOW + ChatColor.BOLD + "+7%" + ChatColor.GRAY + " de" + ChatColor.YELLOW + ChatColor.BOLD + " vitesse" + ChatColor.GRAY + ")");
                player.setWalkSpeed((player.getWalkSpeed() / 100) * 107);
                break;
            case 4:
                setPlayerLame(playerUUID, LameType.NOFALL);
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vous avez reçu la lame de nichirine " + ChatColor.GREEN + ChatColor.BOLD + "verte" + ChatColor.GRAY + ". \n" + ChatColor.GRAY + "(" + ChatColor.GREEN + ChatColor.BOLD + "NoFall" + ChatColor.GRAY +")");
                break;
        }

        player.setItemInHand(new ItemStack(Material.AIR));
    }
}
