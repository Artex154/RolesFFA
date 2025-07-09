package be.artex.rolesffaold.api.role;

import be.artex.rolesffaold.Stacks;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum RoleType {
    DPS(Stacks.DPS),
    TANK(Stacks.TANK),
    TECHNIQUE(Stacks.TECHNIQUE),
    ;

    private final List<Role> roles = new ArrayList<>();
    private final ItemStack stack;

    RoleType(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getItemStack() {
        return stack;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
