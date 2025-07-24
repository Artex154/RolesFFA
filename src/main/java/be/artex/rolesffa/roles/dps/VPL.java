package be.artex.rolesffa.roles.dps;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.StringHelper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VPL extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.REDSTONE)
            .name(ChatColor.RED + "Vilain Petit Loup")
            .lore(" ",
                    ChatColor.GRAY + " Le Vilain Petit Loup possède " + StringHelper.SPEED_SIGN + ChatColor.AQUA + " 120% de Vitesse" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Ainsi que " + StringHelper.STRENGTH_SIGN + ChatColor.RED + " 115% de Force" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public String getName() {
        return "Vilain Petit Loup";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.DPS;
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
    public int getStrength() {
        return 115;
    }
}
