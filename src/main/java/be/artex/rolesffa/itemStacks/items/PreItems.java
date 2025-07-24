package be.artex.rolesffa.itemStacks.items;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

// TODO: Why is this an enum ? A final class with static fields should do the job.
public enum PreItems implements Item {
    // TODO: Put directly inside of be.artex.rolesffa.items.RoleSelection
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
        return stack.clone(); // TODO: Clone should probably be removed, wastes memory allocation by creating new objects for nothing.
    }
}
