package be.artex.rolesffaold.listener.inventory;

import be.artex.rolesffaold.Main;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.api.role.RoleType;
import be.artex.rolesffaold.Stacks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    private static final int[] BORDER_SLOTS = {0, 1, 7, 8, 9, 17, 18, 26, 27, 28, 34, 35};

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack stack = event.getCurrentItem();

        if (stack == null)
            return;

        if (stack.equals(Stacks.border())) {
            event.setCancelled(true);
            return;
        }

        RoleType type = findRoleType(stack);

        if (type != null) {
            openRoleInventory(event, type);
            return;
        }

        Role role = findRole(stack);

        if (role != null)
            assignRole(role, event);
    }

    private static Inventory createRoleInventory(RoleType type) {
        Inventory inv = Bukkit.createInventory(null, 36, "Rôles");

        for (int i : BORDER_SLOTS)
            inv.setItem(i, Stacks.border());

        int index = 10;
        for (Role role : type.getRoles()) {
            if (index == 18)
                index = 19;
            inv.setItem(index++, role.getItemStack());
        }

        return inv;
    }

    private static RoleType findRoleType(ItemStack stack) {
        for (RoleType type : RoleType.values()) {
            if (!stack.equals(type.getItemStack()))
                continue;

            return type;
        }

        return null;
    }

    private static Role findRole(ItemStack stack) {
        for (Role role : Role.registeredRoles) {
            if (!stack.equals(role.getItemStack()))
                continue;

            return role;
        }

        return null;
    }

    private static void openRoleInventory(InventoryClickEvent event, RoleType type) {
        final Inventory INV = createRoleInventory(type);

        INV.clear();

        for (int i : BORDER_SLOTS)
            INV.setItem(i, Stacks.border());

        int index = 10;

        for (Role role : type.getRoles()) {
            if (index == 18)
                index = 19;

            INV.setItem(index++, role.getItemStack());
        }

        event.getWhoClicked().closeInventory();

        Bukkit.getScheduler().runTask(Main.instance, () -> event.getWhoClicked().openInventory(INV));
    }

    private static void assignRole(Role role, InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        role.onAssigned(player);
        player.closeInventory();

        event.setCancelled(true);
    }
}
