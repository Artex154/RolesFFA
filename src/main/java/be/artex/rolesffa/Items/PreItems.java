package be.artex.rolesffa.Items;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum PreItems {
    CHOOSE_BOOK(new ItemBuilder(Material.ENCHANTED_BOOK)
                    .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Sélectionne un rôle")
                    .build()),
    ;

    private final ItemStack stack;

    PreItems(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack.clone();
    }
}
