package be.artex.rolesffa.gui;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.util.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class ChoiceOfRoleGUI {
    private static final HashMap<RoleType, Inventory> inventoryHashMap = new HashMap<>();

    private final static int[] borderIndex =
            {0, 1, 7, 8,
                    9, 17,
                    36, 44,
                    45, 46, 52, 53};

    private static Inventory generateInventory(RoleType type) {
        Inventory inv = Bukkit.createInventory(null, 54, "Rôles " + type.getName());

        for (int i : borderIndex)
            inv.setItem(i, Stacks.BORDER);

        int slot = 19;

        for (Role role : type.getRoles()) {
            inv.setItem(slot, role.getStack());
            slot++;
        }

        return inv;
    }

    public static Inventory getInventory(RoleType type) {
        if (inventoryHashMap.containsKey(type))
            return InventoryUtils.cloneInventory(inventoryHashMap.get(type));

        Inventory inv = generateInventory(type);

        inventoryHashMap.put(type, inv);

        return InventoryUtils.cloneInventory(inv);
    }
}
