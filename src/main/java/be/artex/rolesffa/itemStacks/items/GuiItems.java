package be.artex.rolesffa.itemStacks.items;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GuiItems implements Item {
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
        return this.stack.clone();
    }
}
