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

    public static final ItemStack LG = ItemBuilder.create(Material.REDSTONE)
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "LOUP-GAROUS")
            .build();

    public static final ItemStack SANEMI = ItemBuilder.create(Material.QUARTZ)
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sanemi")
            .setLore(" ",
                    ChatColor.GRAY + " • Il possède " + ChatColor.AQUA + "Vitesse I" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " • Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " • Il possède l'" + ChatColor.AQUA + "Immense Speed" + ChatColor.GRAY + " qui lui permet d'effectuer un " + ChatColor.AQUA + "dash" + ChatColor.GRAY + ".      ",
                    ChatColor.GRAY + " • Cooldown: " + ChatColor.YELLOW + "70 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack LG_ROLE = ItemBuilder.create(Material.ROTTEN_FLESH)
            .displayName(ChatColor.RED + "Loup-Garou")
            .setLore(" ",
                    ChatColor.GRAY + " • Il possède " + ChatColor.RED + "Force I" + ChatColor.GRAY + ".", " ")
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

    public static final ItemStack LAME_DE_NICHIRINE = ItemBuilder.create(Material.NETHER_STAR)
            .displayName(ChatColor.AQUA + "Lame de Nichirine")
            .setLore(ChatColor.GRAY + "Clique droit pour avoir une lame aléatoire entre:", " ",
                    ChatColor.GRAY + " " + StringUtils.dot + ChatColor.DARK_GRAY + " Lame de Force (+5% de dégats)",
                    ChatColor.GRAY + " " + StringUtils.dot + ChatColor.YELLOW + " Lame de Vitesse (+7% de vitesse)",
                    ChatColor.GRAY + " " + StringUtils.dot + ChatColor.LIGHT_PURPLE + " Lame de Vie (+2 coeurs)",
                    ChatColor.GRAY + " " + StringUtils.dot + ChatColor.GRAY + " Lame de Résistance (+5% de résistance)",
                    ChatColor.GRAY + " " + StringUtils.dot + ChatColor.GREEN + " Lame de NoFall", " ")
            .build();

    public static final ItemStack IMMENSE_SPEED = ItemBuilder.create(Material.NETHER_STAR)
            .displayName(ChatColor.AQUA + "Immense Speed")
            .setLore(ChatColor.GRAY + " " + StringUtils.dot + " En faissant clique droit vous ferez un " + ChatColor.AQUA + "dash.",
                    ChatColor.GRAY + " " + StringUtils.dot + " Vous aurez " + ChatColor.GREEN + "NoFall" + ChatColor.GRAY + " pendant " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + " après votre " + ChatColor.AQUA + "dash" + ChatColor.GRAY + ".",
                    ChatColor.GRAY + " " + StringUtils.dot + " Cooldown: " + ChatColor.YELLOW + "70 secondes")
            .build();

    public static ItemStack border() {
        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
        ItemMeta itemMeta = border.getItemMeta();
        itemMeta.setDisplayName(" ");
        border.setItemMeta(itemMeta);

        return border;
    }
}
