package be.artex.rolesffa.api;

import be.artex.rolesffa.util.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public enum Team {
    SLAYER(20, Stacks.SLAYERS, Bukkit.createInventory(null, 54, "Slayers")),
    SHINOBIS(21, Stacks.SHINOBIS, Bukkit.createInventory(null, 54, "Shinobis")),
    SOLDATS(22, Stacks.SOLDATS, Bukkit.createInventory(null, 54, "Soldats")),
    PIRATES(23, Stacks.PIRATES, Bukkit.createInventory(null, 54, "Pirates")),;

    private final int placement;
    private final ItemStack stack;
    private final Inventory inventory;

    Team(int placement, ItemStack stack, Inventory inventory) {
        this.placement = placement;
        this.stack = stack;
        this.inventory = inventory;
    }

    public ItemStack getItemStack() {
        return stack;
    }

    public int getPlacement() {
        return placement;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
