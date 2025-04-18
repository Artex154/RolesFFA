package be.artex.rolesffa;

import be.raft.crafty.item.Item;
import be.raft.crafty.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Stacks {
    public static final ItemStack DPS = new ItemBuilder<>(new ItemStack(Material.DIAMOND_SWORD))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "DPS")
            .build();

    public static final ItemStack TANK = new ItemBuilder<>(new ItemStack(Material.DIAMOND_CHESTPLATE))
            .displayName(ChatColor.BLUE + "" + ChatColor.BOLD + "TANK")
            .build();

    public static final ItemStack TECHNIQUE = new ItemBuilder<>(new ItemStack(Material.REDSTONE_LAMP_OFF))
            .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "TECHNIQUE")
            .build();

    public static final ItemStack SANEMI = new ItemBuilder<>(new ItemStack(Material.QUARTZ))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Sanemi")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.RED + "+25% de force" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Pour chaque " + ChatColor.AQUA + "joueur " + ChatColor.GRAY + "qu'il tue, il gagne " + ChatColor.YELLOW + "7% de vitesse" + ChatColor.GRAY + ".    ", " ",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack GYOMEI = new ItemBuilder<>(new ItemStack(Material.DIAMOND_AXE))
            .displayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Gyomei")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède resistance I.", " ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.AQUA + "Kusarigama" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack EJIRO = new ItemBuilder<>(new ItemStack(Material.DIAMOND_CHESTPLATE))
            .displayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Ejiro")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède Resistance I.", " ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.AQUA + "Unbreakable" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack OBANAI = new ItemBuilder<>(new ItemStack(Material.GHAST_TEAR))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Obanai")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède le " + ChatColor.GREEN + "souffle du serpent" + ChatColor.GRAY + ".",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack SABITO = new ItemBuilder<>(new ItemStack(Material.IRON_SWORD))
            .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sabito")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède son " + ChatColor.AQUA + "dash" + ChatColor.GRAY + ".",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Ses bottes possèdent " + ChatColor.BLUE + "depth strider 2" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack CUPIDON = new ItemBuilder<>(new ItemStack(Material.BOW))
            .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Cupidon Rancunier")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède son " + ChatColor.GREEN + "Philtrum" + ChatColor.GRAY + " et une" + ChatColor.AQUA + " épée tranhant 4" +ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack TOMURA = new ItemBuilder<>(new ItemStack(Material.SULPHUR))
            .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Tomura")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède ses " + ChatColor.BOLD + "Mains" + ChatColor.GRAY + " avec les qu'elles il peut désactiver l'item en main de son ennemi.", " ")
            .build();

    public static final ItemStack VPL = new ItemBuilder<>(new ItemStack(Material.ROTTEN_FLESH))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Vilain Petit Loup")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.RED + "+15% de force" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1" + ChatColor.GRAY + ".    ", " ")
            .build();

    public static final ItemStack KILLUA = new ItemBuilder<>(new ItemStack(Material.BLAZE_ROD))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Killua")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1 " + ChatColor.GRAY + "de façon permanente.", " ",
                    Main.dot + ChatColor.GRAY + "Après son dixième coup, il inflige " + ChatColor.AQUA + "un éclair " + ChatColor.GRAY + "qui fait " + ChatColor.RED + "1.5 coeurs de dégats" + ChatColor.GRAY + " et donne ", ChatColor.YELLOW + " vitesse 2 " + ChatColor.GRAY + " pendant " + ChatColor.YELLOW + "4 secondes" + ChatColor.GRAY + ".    ", " ")
            .build();

    public static final ItemStack SHOTO = new ItemBuilder<>(new ItemStack(Material.ICE))
                .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Shoto")
                .setLore(" ",
                        Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.GOLD + "resistance au feu" + ChatColor.GRAY + ".", " ",
                        Main.dot + ChatColor.GRAY + "Il possède son coté " + ChatColor.AQUA + "gelé" + ChatColor.GRAY + " et son coté " + ChatColor.GOLD + "enflammé" + ChatColor.GRAY + ". ", "    ")
                .build();

    public static final ItemStack MUICHIRO = new ItemBuilder<>(new ItemStack(Material.DIAMOND))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Muichiro")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.YELLOW + "vitesse 1" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède le " + ChatColor.DARK_AQUA + "Souffle de la Brume" + ChatColor.GRAY + ".",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack KNUCKLES = new ItemBuilder<>(new ItemStack(Material.BLAZE_POWDER))
            .displayName(ChatColor.RED + "" + ChatColor.BOLD + "Knuckles")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.RED + "+15% de force" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un joueur, il possède " + ChatColor.AQUA + "15% de chance " + ChatColor.GRAY + "de mettre en" + ChatColor.GOLD + " feu" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack KYOJURO = new ItemBuilder<>(new ItemStack(Material.BLAZE_POWDER))
            .displayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Kyojuro")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "Il possède " + ChatColor.GOLD + "resistance au feu" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "Il possède un arc " + ChatColor.GOLD + "flame" + ChatColor.GRAY + ".",
                    Main.dot + ChatColor.GRAY + "Il possède le " + ChatColor.GOLD + ChatColor.BOLD + "Purgatoire" + ChatColor.GRAY + ".",
                    Main.dot + ChatColor.GRAY + "Il possède une " + ChatColor.AQUA + "Lame de Nichirine" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack LAME_DE_NICHIRINE = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lame de Nichirine")
            .setLore(ChatColor.GRAY + "Clique droit pour avoir une lame aléatoire entre:", " ",
                    Main.dot + "Lame de Force (+5% de dégats)",
                    Main.dot + ChatColor.YELLOW + "Lame de Vitesse (+7% de vitesse)",
                    Main.dot + ChatColor.LIGHT_PURPLE + "Lame de Vie (+2 coeurs)",
                    Main.dot + ChatColor.GRAY + "Lame de Résistance (+5% de résistance)",
                    Main.dot + ChatColor.GREEN + "Lame de NoFall", " ")
            .build();

    public static final ItemStack UNBREAKABLE = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Unbreakable")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En faissant un clique, vous " + ChatColor.AQUA + "recevrez" + ChatColor.DARK_GRAY + " weakness 1" + ChatColor.GRAY + " et resistance 2 pendant " + ChatColor.YELLOW + " 7 secondes" + ChatColor.GRAY + ".    ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + "80 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack KUSARIGAMA = new ItemBuilder<>(new ItemStack(Material.IRON_AXE))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Kusarigama")
            .addEnchant(Enchantment.ARROW_DAMAGE, 1)
            .addItemFlags(ItemFlag.HIDE_ENCHANTS)
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En faissant un clique, vous téléporterez tout les " + ChatColor.AQUA + "joueurs" + ChatColor.GRAY + " dans un rayon de 50 blocs sur vous.  ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 90 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack SOUFFLE = new ItemBuilder<>(new ItemStack(Material.IRON_SWORD))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Souffle du Serpent")
            .addEnchant(Enchantment.ARROW_DAMAGE, 1)
            .addItemFlags(ItemFlag.HIDE_ENCHANTS)
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En tappant quelqu'un, vous lui infligerez " + ChatColor.GREEN + "poison 2" + ChatColor.GRAY + " pendant " + ChatColor.GREEN + "5 secondes" + ChatColor.GRAY + ".  ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack SOUFFLEB = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Souffle de la brume")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En faissant un clique droit, vous infligerez " + ChatColor.DARK_GRAY + "blindness I et weakness I" + ChatColor.GRAY + " pendant " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + ".  ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 70 secondes" + ChatColor.GRAY + ".", " ")
            .build();


    public static final ItemStack UNUSABLE = new ItemBuilder<>(new ItemStack(Material.BARRIER))
            .displayName(ChatColor.RED + "Unusable")
            .setLore(Main.dot + ChatColor.GRAY + "Cette item à été désactivé par " + ChatColor.RED + "Tomura" + ChatColor.GRAY + ".")
            .build();

    public static final ItemStack MAINS = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Vos Mains")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un joueur, vous désactiverez son item pendant 5 secondes.",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack ICE = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Votre Coté Gelé")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un joueur, vous faites apparaitre un cocon de " + ChatColor.AQUA + "glace" + ChatColor.GRAY + " autour de lui.    ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack FIRE = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Votre Coté Enflammé")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un joueur, vous " + ChatColor.GOLD + "enflammerez" + ChatColor.GRAY + " le joueur.  ", " ")
            .build();

    public static final ItemStack PHILTRUM = new ItemBuilder<>(new ItemStack(Material.BOW))
            .displayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Philtrum")
            .addEnchant(Enchantment.ARROW_DAMAGE, 3)
            .addEnchant(Enchantment.ARROW_KNOCKBACK, 1)
            .build();

    public static ItemStack SABITO_DASH = new ItemBuilder<>(new ItemStack(Material.NETHER_STAR))
            .displayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Dash")
            .build();

    public static ItemStack PURGATOIRE = new ItemBuilder<>(new ItemStack(Material.MAGMA_CREAM))
            .addEnchant(Enchantment.ARROW_KNOCKBACK, 1)
            .addItemFlags(ItemFlag.HIDE_ENCHANTS)
            .displayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Purgatoire")
            .setLore(" ",
                    Main.dot + ChatColor.GRAY + "En faissant un clique droit, vous gagnerez " + ChatColor.YELLOW + "Speed II" + ChatColor.GRAY + ". ",
                    Main.dot + ChatColor.GRAY + "Quand vous infligerez un " + ChatColor.RED + "coup" + ChatColor.GRAY + " sur un joueur, vous le metterez en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ".      ",
                    Main.dot + ChatColor.GRAY + "Ces pouvoirs durent" + ChatColor.YELLOW + " 45 secondes" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "En plus de mettre le joueur en " + ChatColor.GOLD + "feu" + ChatColor.GRAY + ", tout vos prochains coups dans les dix secondes qui suivent,    ",
                    ChatColor.GRAY + "   vous enlèverez " + ChatColor.LIGHT_PURPLE + "1 demi-coeur " + ChatColor.GRAY + "permanent au joueur tappé.",
                    Main.dot + ChatColor.GRAY + "Les coeurs sont redonnés après " + ChatColor.YELLOW + "1 minute" + ChatColor.GRAY + ".", " ",
                    Main.dot + ChatColor.GRAY + "En contre partie, vous perdrez" + ChatColor.LIGHT_PURPLE  + " 2 coeurs " + ChatColor.GRAY + "permanent pendant " + ChatColor.YELLOW + "1 minute 30" + ChatColor.GRAY + ".  ", " ",
                    Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 1x/kill" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ItemStack CHOOSE_BOOK = new ItemBuilder<>(new ItemStack(Material.ENCHANTED_BOOK))
            .displayName(ChatColor.RESET + "Choisis ton rôle")
            .build();

    public static ItemStack head(int amount) {
        List<String> list = new ArrayList<>();
        list.add(" ");
        list.add(Main.dot + ChatColor.GRAY + "En faissant un clique, vous " + ChatColor.AQUA + "mangerez" + ChatColor.YELLOW + " la pomme " + ChatColor.GRAY + "et vous gagnerez " + ChatColor.YELLOW + "20% de vitesse" + ChatColor.GRAY + " et     ");
        list.add(ChatColor.LIGHT_PURPLE + "régénération 2 " + ChatColor.GRAY + "et " + ChatColor.YELLOW + "absorption 2" + ChatColor.GRAY + " pendant 10 secondes.");
        list.add(" ");

        Item item = new Item(new ItemStack(Material.SKULL_ITEM));
        item.setName(ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Head");
        item.setLore(list);
        item.setAmount(amount);

        return item.toItemStack();
    }

    public static ItemStack border() {
        Item border = new Item(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3));

        border.setName(" ");

        return border.toItemStack();
    }

}
