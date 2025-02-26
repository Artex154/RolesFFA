package be.artex.rolesffa.listener.inventory;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null)
            return;

        if (event.getCurrentItem().equals(Stacks.border())) {
            event.setCancelled(true);
            return;
        }

        Team foundTeam = null;

        for (Team team : Team.values()) {
            if (team.getItemStack().equals(event.getCurrentItem())) {
                foundTeam = team;
                break;
            }
        }

        if (foundTeam != null) {
            Inventory teamInventory = foundTeam.getInventory();

            for (Role role : RoleUtils.registeredRoles) {
                if (role.getCamp().equals(foundTeam)) {
                    teamInventory.setItem(role.getPlacement(), role.getItemStack());
                }
            }

            teamInventory.setItem(0, Stacks.border());
            teamInventory.setItem(1, Stacks.border());
            teamInventory.setItem(7, Stacks.border());
            teamInventory.setItem(8, Stacks.border());
            teamInventory.setItem(9, Stacks.border());
            teamInventory.setItem(17, Stacks.border());
            teamInventory.setItem(18, Stacks.border());
            teamInventory.setItem(26, Stacks.border());
            teamInventory.setItem(27, Stacks.border());
            teamInventory.setItem(28, Stacks.border());
            teamInventory.setItem(34, Stacks.border());
            teamInventory.setItem(35, Stacks.border());

            event.getWhoClicked().openInventory(foundTeam.getInventory());
            event.setCancelled(true);
            return;
        }

        Role foundRole = null;

        for (Role role : RoleUtils.registeredRoles) {
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
