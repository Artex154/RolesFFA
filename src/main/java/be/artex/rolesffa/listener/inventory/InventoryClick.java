package be.artex.rolesffa.listener.inventory;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    public static Inventory inv = Bukkit.createInventory(null, 36, "Rôles");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null)
            return;

        if (event.getCurrentItem().equals(Stacks.border())) {
            event.setCancelled(true);
            return;
        }

        RoleType foundTeam = null;

        for (RoleType team : RoleType.values()) {
            if (team.getItemStack().equals(event.getCurrentItem())) {
                foundTeam = team;
                System.out.println(team.getItemStack().getItemMeta().getDisplayName());
                break;
            }
        }

        if (foundTeam != null) {
            inv.clear();

            int[] slots = {0, 1, 7, 8, 9, 17, 18, 26, 27, 28, 34, 35};

            for (int i : slots) {
                inv.setItem(i, Stacks.border());
            }

            int i = 10;

            for (Role role : foundTeam.getRoles()) {
                if (i == 18)
                    i = 19;

                inv.setItem(i, role.getItemStack());
                i++;
            }

            event.getWhoClicked().closeInventory();

            Bukkit.getScheduler().runTask(Main.instance, () -> event.getWhoClicked().openInventory(inv));
        }

        Role foundRole = null;

        for (Role role : Role.registeredRoles) {
            if (role.getItemStack().equals(event.getCurrentItem())) {
                foundRole = role;
                break;
            }
        }

        if (foundRole != null) {
            foundRole.onAssigned((Player) event.getWhoClicked());
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
        }
    }
}
