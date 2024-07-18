package be.artex.rolesffa.util;

import be.raft.crafty.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Stacks {
    public static final ItemStack CHOOSE_BOOK = ItemBuilder.create(Material.ENCHANTED_BOOK)
            .displayName(ChatColor.RESET + "Choisis ton rôle")
            .build();

    public static final ItemStack SLAYER = ItemBuilder.create(Material.IRON_SWORD)
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SLAYER")
            .build();

    public static final ItemStack SANEMI = ItemBuilder.create(Material.QUARTZ)
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sanemi")
            .setLore(" ",
                    ChatColor.GRAY + " • Il possède " + ChatColor.AQUA + "vitesse I" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " • Il possède l'" + ChatColor.AQUA + "Immense Speed" + ChatColor.GRAY + " qui lui permet d'effectuer un " + ChatColor.AQUA + "dash" + ChatColor.GRAY + ".      ", ChatColor.GRAY + " • Cooldown: " + ChatColor.YELLOW + "70 secondes" + ChatColor.GRAY + ".", " ", ChatColor.GRAY + " • Inspiration: " + ChatColor.YELLOW + "UHCWorld - Nakime Party" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack DPS = ItemBuilder.create(Material.DIAMOND_SWORD)
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "DPS")
            .build();

    public static final ItemStack TANK = ItemBuilder.create(Material.DIAMOND_CHESTPLATE)
            .displayName(ChatColor.BLUE + "" + ChatColor.BOLD + "TANK")
            .build();

    public static final ItemStack HEALER = ItemBuilder.create(Material.GOLDEN_APPLE)
            .displayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "HEALER")
            .build();

    public static ItemStack border() {
        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemMeta itemMeta = border.getItemMeta();
        itemMeta.setDisplayName(" ");
        border.setItemMeta(itemMeta);

        return border;
    }
}
