package be.artex.rolesffa.itemStacks.items;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

// TODO: Why is this an enum ? A final class with static fields should do the job.
public enum GuiItems implements Item {
    // TODO: Should probably be a constant somewhere else
    BORDER(new ItemBuilder(Material.STAINED_GLASS_PANE)
            .name(" ")
            .durability(15)
            .build());

    private final ItemStack stack;

    GuiItems(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ItemStack getStack() {
        return this.stack.clone(); // TODO: Clone should probably be removed, wastes memory allocation by creating new objects for nothing.
    }
}
