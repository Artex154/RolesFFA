package be.artex.rolesffa.item;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.Stacks;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Choose extends SPItem {
    public static Inventory inv = Bukkit.createInventory(null, 27, "Teams");

    @Override
    public ItemStack getItemStack() {
        return Stacks.CHOOSE_BOOK;
    }

    @Override
    public TextComponent getDescription() {
        return null;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        int[] slots = {0, 1, 7, 8, 9, 17, 18, 19, 25, 26};

        for (int i : slots) {
            inv.setItem(i, Stacks.border());
        }

        int i = 12;

        for (RoleType team : RoleType.values()) {
            inv.setItem(i, team.getItemStack());
            i++;
        }

        event.getPlayer().openInventory(inv);

    }
}
