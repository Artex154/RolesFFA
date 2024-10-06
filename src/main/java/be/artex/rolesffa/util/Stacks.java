package be.artex.rolesffa.util;

import be.raft.crafty.item.Item;
import be.raft.crafty.item.ItemBuilder;
import com.sun.org.apache.bcel.internal.generic.IREM;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Stacks {
    public static final ItemStack CHOOSE_BOOK = new ItemBuilder<>(new ItemStack(Material.ENCHANTED_BOOK))
            .displayName(ChatColor.RESET + "Choisis ton rôle")
            .build();

    public static final ItemStack SLAYERS = new ItemBuilder<>(new ItemStack(Material.IRON_SWORD))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SLAYERS")
            .build();

    public static final ItemStack SOLDATS = new ItemBuilder<>(new ItemStack(Material.BOW))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SOLDATS")
            .build();

    public static final ItemStack SHINOBIS = new ItemBuilder<>(new ItemStack(Material.EMERALD))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "SHINOBIS")
            .build();

    public static final ItemStack PIRATES = new ItemBuilder<>(new ItemStack(Material.BOAT))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "PIRATES")
            .build();

    public static final ItemStack SUP_VIL = new ItemBuilder<>(new ItemStack(Material.REDSTONE))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "ASSOCIATION DES SUPER VILLAINS")
            .build();

    public static final ItemStack HUNTERS = new ItemBuilder<>(new ItemStack(Material.FISHING_ROD))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "HUNTERS")
            .build();

    public static final ItemStack SANEMI = new ItemBuilder<>(new ItemStack(Material.QUARTZ))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sanemi")
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède " + ChatColor.RED + "+30% de force" + ChatColor.GRAY + ".", " ",
                    StringUtils.dot + ChatColor.GRAY + "Pour chaque " + ChatColor.AQUA + "joueur " + ChatColor.GRAY + "qu'il tue, il gagne " + ChatColor.YELLOW + "7% de vitesse" + ChatColor.GRAY + ".    ", " ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack SABITO = new ItemBuilder<>(new ItemStack(Material.IRON_SWORD))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Sabito")
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1" + ChatColor.GRAY + ".", " ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède son " + ChatColor.AQUA + "dash" + ChatColor.GRAY + ".",
                    StringUtils.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ",
                    StringUtils.dot + ChatColor.GRAY + "Ses bottes possèdent " + ChatColor.BLUE + "depth strider 2" + ChatColor.GRAY + ".", " ")
            .build();


    public static final ItemStack TOMURA = new ItemBuilder<>(new ItemStack(Material.SULPHUR))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Tomura")
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède ses " + ChatColor.BOLD + "Mains" + ChatColor.GRAY + " avec les quelles il peut désactiver l'item en main de son ennemi.", " ")
            .build();

    public static final ItemStack LAME_DE_NICHIRINE = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lame de Nichirine")
            .setLore(ChatColor.GRAY + "Clique droit pour avoir une lame aléatoire entre:", " ",
                    StringUtils.dot + "Lame de Force (+5% de dégats)",
                    StringUtils.dot + ChatColor.YELLOW + "Lame de Vitesse (+7% de vitesse)",
                    StringUtils.dot + ChatColor.LIGHT_PURPLE + "Lame de Vie (+2 coeurs)",
                    StringUtils.dot + ChatColor.GRAY + "Lame de Résistance (+5% de résistance)",
                    StringUtils.dot + ChatColor.GREEN + "Lame de NoFall", " ")
            .build();

    public static final ItemStack KUSARIGAMA = new ItemBuilder<>(new ItemStack(Material.IRON_AXE))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Kusarigama")
            .addEnchant(Enchantment.ARROW_DAMAGE, 1)
            .addItemFlags(ItemFlag.HIDE_ENCHANTS)
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "En faissant un clique, vous téléporterez tout les " + ChatColor.AQUA + "joueurs" + ChatColor.GRAY + " dans un rayon de 50 blocs sur vous.  ",
                    StringUtils.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 70 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack UNUSABLE = new ItemBuilder<>(new ItemStack(Material.BARRIER))
            .displayName(ChatColor.RED + "Unusable")
            .setLore(StringUtils.dot + ChatColor.GRAY + "Cette item à été désactivé par " + ChatColor.RED + "Tomura" + ChatColor.GRAY + ".")
            .build();

    public static final ItemStack MAINS = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Mains")
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "En frappant quelqu'un vous désactiverez son item pendant 5 secondes.",
                    StringUtils.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack GYOMEI = new ItemBuilder<>(new ItemStack(Material.IRON_AXE))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Gyomei")
            .setLore(" ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède Resistance I.", " ",
                    StringUtils.dot + ChatColor.GRAY + "Il possède " + ChatColor.AQUA + "Kusarigama" + ChatColor.GRAY + ".",
                    StringUtils.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static ItemStack border() {
        Item border = new Item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3));

        border.setName(" ");

        return border.toItemStack();
    }

    public static ItemStack SABITO_DASH = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Dash")
            .build();
}
