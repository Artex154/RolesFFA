package be.artex.rolesffa.listener.inventory;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.gui.ChoiceOfRoleGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

        RoleType type = RoleUtils.getRoleTypeFromItem(stack);

        if (type != null) {
            event.setCancelled(true);

            player.closeInventory();

            Bukkit.getScheduler().runTask(Main.instance, () ->
                    player.openInventory(ChoiceOfRoleGUI.getInventory(type))
            );

            return;
        }

        Role role = RoleUtils.getRoleFromItem(stack);

        if (role != null) {
            event.setCancelled(true);

            player.closeInventory();

            RoleUtils.playerSetup(player, role);
        }
    }
}
