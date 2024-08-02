package be.artex.rolesffa.api.items;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.lame.LameType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
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

    public static boolean hasPlayerALame(UUID uuid) {
        return playerLames.get(uuid) == null;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.LAME_DE_NICHIRINE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(getItemStack().getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(getItemStack().getItemMeta().getDisplayName() +
                "\n" + ChatColor.GRAY + "Clique droit pour avoir une lame aléatiore entre:\n\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.DARK_GRAY + " Lame de Force (+5% de dégats)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.YELLOW + " Lame de Vitesse (+7% de vitesse)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.LIGHT_PURPLE + " Lame de Vie (+2 coeurs)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.GRAY + " Lame de Résistance (+5% de résistance)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.GREEN + " Lame de NoFall\n")
                });

        description.setHoverEvent(event);
        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        switch (new Random().nextInt(5)) {
            case 0:
                setPlayerLame(playerUUID, LameType.STRENGTH);
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_AQUA + ChatColor.BOLD + "noire" + ChatColor.AQUA + ". \n" + ChatColor.DARK_AQUA + "(" + ChatColor.AQUA + "+5% de force" + ChatColor.DARK_AQUA + ")");
                break;
            case 1:
                setPlayerLame(playerUUID, LameType.RESISTANCE);
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_AQUA + ChatColor.BOLD + "grise" + ChatColor.AQUA + ". \n" + ChatColor.DARK_AQUA + "(" + ChatColor.AQUA + "+5% de resistance" + ChatColor.DARK_AQUA + ")");
                break;
            case 2:
                setPlayerLame(playerUUID, LameType.LIFE);
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_AQUA + ChatColor.BOLD + "vie" + ChatColor.AQUA + ". \n" + ChatColor.DARK_AQUA + "(" + ChatColor.AQUA + "+2 coeurs" + ChatColor.DARK_AQUA + ")");
                player.setMaxHealth(player.getMaxHealth() + 4);
                break;
            case 3:
                setPlayerLame(playerUUID, LameType.SPEED);
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_AQUA + ChatColor.BOLD + "jaune" + ChatColor.AQUA + ". \n" + ChatColor.DARK_AQUA + "(" + ChatColor.AQUA + "+7 de vitesse" + ChatColor.DARK_AQUA + ")");
                player.setWalkSpeed((player.getWalkSpeed() / 100) * 107);
                break;
            case 4:
                setPlayerLame(playerUUID, LameType.NOFALL);
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez reçu la lame de nichirine " + ChatColor.DARK_AQUA + ChatColor.BOLD + "verte" + ChatColor.AQUA + ". \n" + ChatColor.DARK_AQUA + "(" + ChatColor.AQUA + "NoFall" + ChatColor.DARK_AQUA + ")");
                break;
        }

        player.setItemInHand(new ItemStack(Material.AIR));
    }
}
