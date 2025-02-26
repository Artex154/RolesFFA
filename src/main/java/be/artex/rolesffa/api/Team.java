package be.artex.rolesffa.api;

import be.artex.rolesffa.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public enum Team {
    DPS(12, Stacks.DPS, Bukkit.createInventory(null, 36, "Rôles DPS")),
    TANK(13, Stacks.TANK, Bukkit.createInventory(null, 36, "Rôles Tanks")),
    TECHNIQUE(14, Stacks.TECHNIQUE, Bukkit.createInventory(null, 36, "Rôles Techniques")),
    ;

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
