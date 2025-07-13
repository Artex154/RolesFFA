package be.artex.rolesffa.api.role;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum RoleType {
    DPS(new ItemBuilder(Material.INK_SACK)
            .name(ChatColor.RED + "" + ChatColor.BOLD + "DPS")
            .durability(1)
            .build(),
            "DPS"),
    TECHNIQUE(new ItemBuilder(Material.INK_SACK)
            .name(ChatColor.YELLOW + "" + ChatColor.BOLD + "TECHNIQUE")
            .durability(11)
            .build(),
            "Techniques"),
    TANK(new ItemBuilder(Material.INK_SACK)
            .name(ChatColor.BLUE + "" + ChatColor.BOLD + "TANK")
            .durability(4)
            .build(),
            "Tanks");

    private final ItemStack item;
    private final String name;
    private final List<Role> roles;

    RoleType(ItemStack item, String name) {
        this.item = item;
        this.name = name;
        this.roles = new ArrayList<>();
    }

    public ItemStack getItem() {
        return item.clone();
    }

    public String getName() {
        return name;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
