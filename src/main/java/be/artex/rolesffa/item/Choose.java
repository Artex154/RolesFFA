package be.artex.rolesffa.item;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.api.Team;
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
        inv.setItem(0, Stacks.border());
        inv.setItem(1, Stacks.border());
        inv.setItem(7, Stacks.border());
        inv.setItem(8, Stacks.border());
        inv.setItem(9, Stacks.border());
        inv.setItem(17, Stacks.border());
        inv.setItem(18, Stacks.border());
        inv.setItem(19, Stacks.border());
        inv.setItem(25, Stacks.border());
        inv.setItem(26, Stacks.border());

        for (Team team : Team.values())
            inv.setItem(team.getPlacement(), team.getItemStack());

        event.getPlayer().openInventory(inv);

    }
}
