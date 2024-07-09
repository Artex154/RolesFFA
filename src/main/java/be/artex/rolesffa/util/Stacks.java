package be.artex.rolesffa.util;

import be.raft.crafty.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Stacks {
    public static final ItemStack CHOOSE_BOOK = ItemBuilder.create(Material.ENCHANTED_BOOK)
            .displayName(ChatColor.RESET + "Choisis ton rôle")
            .build();
}
