package be.artex.rolesffa.listener.inventory;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.gui.ChoiceOfRoleGUI;
import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.util.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack stack = event.getCurrentItem();

        if (stack == null)
            return;

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();

        if (inv.getHolder() != null && inv.getHolder().equals(player))
            return;

        if (stack.isSimilar(Stacks.BORDER)) {
            event.setCancelled(true);
            return;
        }

        RoleType type = Role.getRoleTypeFromItem(stack);

        if (type != null) {
            event.setCancelled(true);

            player.closeInventory();

            Bukkit.getScheduler().runTask(Main.instance, () ->
                    player.openInventory(ChoiceOfRoleGUI.getInventory(type))
            );

            return;
        }

        Role role = RolesRegistries.ROLES.getRoleFromMenuStack(stack);

        if (role != null) {
            event.setCancelled(true);

            player.closeInventory();

            PlayerUtils.playerSetup(player, role);
        }
    }
}
