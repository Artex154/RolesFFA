package be.artex.rolesffa.itemStacks.items;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum PreItems implements Item {
    SLECTION_BOOK(new ItemBuilder(Material.ENCHANTED_BOOK)
                    .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Sélectionne un rôle")
                    .build()),
    ;

    private final ItemStack stack;

    PreItems(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ItemStack getStack() {
        return stack.clone();
    }
}
