package be.artex.rolesffa.roles.tank.gaara;

import be.artex.rolesffa.api.item.RFItem;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Shukaku extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.INK_SACK)
            .durability(11)
            .name(ChatColor.YELLOW + "" + ChatColor.BOLD + "Shukaku")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }
}
