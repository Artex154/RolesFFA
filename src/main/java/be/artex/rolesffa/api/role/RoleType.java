package be.artex.rolesffa.api.role;

import be.artex.rolesffa.Stacks;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum RoleType {
    DPS(Stacks.DPS, new ArrayList<>()),
    TANK(Stacks.TANK, new ArrayList<>()),
    TECHNIQUE(Stacks.TECHNIQUE, new ArrayList<>()),
    ;

    private final ArrayList<Role> roles;
    private final ItemStack stack;

    RoleType(ItemStack stack, ArrayList<Role> roles) {
        this.stack = stack;
        this.roles = roles;
    }

    public ItemStack getItemStack() {
        return stack;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }
}
