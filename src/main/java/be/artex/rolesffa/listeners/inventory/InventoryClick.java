package be.artex.rolesffa.listeners.inventory;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Stacks;
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
            if (team.getItemStack().equals(event.getCurrentItem()))
                foundTeam = team;
        }

        if (foundTeam != null) {
            Inventory teamInventory = foundTeam.getInventory();

            for (Role role : Role.Utils.registeredRoles) {
                if (role.getType().equals(foundTeam) || role.getCamp().equals(foundTeam)) {
                    teamInventory.setItem(role.getPlacement(), role.getItemStack());
                }
            }

            teamInventory.setItem(0, Stacks.border());
            teamInventory.setItem(1, Stacks.border());
            teamInventory.setItem(7, Stacks.border());
            teamInventory.setItem(8, Stacks.border());
            teamInventory.setItem(9, Stacks.border());
            teamInventory.setItem(17, Stacks.border());
            teamInventory.setItem(36, Stacks.border());
            teamInventory.setItem(44, Stacks.border());
            teamInventory.setItem(45, Stacks.border());
            teamInventory.setItem(46, Stacks.border());
            teamInventory.setItem(52, Stacks.border());
            teamInventory.setItem(53, Stacks.border());

            event.getWhoClicked().openInventory(foundTeam.getInventory());
            event.setCancelled(true);
            return;
        }

        Role foundRole = null;

        for (Role role : Role.Utils.registeredRoles) {
            if (role.getItemStack().equals(event.getCurrentItem())) {
                foundRole = role;
            }
        }

        if (foundRole != null) {
            foundRole.onAssigned((Player) event.getWhoClicked());
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
        }
    }
}
