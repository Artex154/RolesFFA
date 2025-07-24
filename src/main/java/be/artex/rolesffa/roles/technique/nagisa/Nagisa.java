package be.artex.rolesffa.roles.technique.nagisa;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.StringHelper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nagisa extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.ENDER_PEARL)
            .name(ChatColor.YELLOW + "Nagisa")
            .lore(" ",
                    ChatColor.GRAY + " Nagisa possède " + StringHelper.SPEED_SIGN + ChatColor.AQUA + " 120% de Vitesse" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Ainsi que son " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public String getName() {
        return "Nagisa";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public int getPlacement() {
        return 19;
    }

    @Override
    public int getSpeed() {
        return 120;
    }

    @Override
    public List<RFItem> getItems() {
        return new ArrayList<>(Collections.singletonList(new CoupParalysant()));
    }
}
