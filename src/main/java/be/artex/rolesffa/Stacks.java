package be.artex.rolesffa;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Stacks {
    public static final ItemStack BORDER = new ItemBuilder(Material.STAINED_GLASS_PANE)
            .name(" ")
            .durability(15)
            .build();

    public static final ItemStack ROLE_SELECTION_BOOK = new ItemBuilder(Material.ENCHANTED_BOOK)
            .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Sélectionne un rôle")
            .build();
}
