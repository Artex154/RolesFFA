package be.artex.rolesffa.roles.technique.machoireTitan;

import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.items.EquipementTridimensionnelle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class TitanMachoire extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.GHAST_TEAR)
            .name(ChatColor.YELLOW + "Titan Mâchoire")
            .lore(" ",
                    ChatColor.GRAY + " Le Titan Mâchoire possède l'" + ChatColor.GOLD + ChatColor.BOLD + "Equipement Tridimensionnelle" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Ainsi que sa " + ChatColor.GOLD + ChatColor.BOLD + "Transformation" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public String getName() {
        return "Titan Mâchoire";
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
        return 20;
    }

    @Override
    public List<RFItem> getItems() {
        return Arrays.asList(new Transformation(), new EquipementTridimensionnelle(), new Machoire());
    }
}
