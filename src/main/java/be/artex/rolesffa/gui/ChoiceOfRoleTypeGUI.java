package be.artex.rolesffa.gui;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ChoiceOfRoleTypeGUI {
    private final static Inventory INVENTORY = Bukkit.createInventory(null, 27, "Types de Rôles");
    private final static int[] BORDER_INDEXES =
            {0, 1, 7, 8,
            9, 17,
            18, 19, 25, 26};

    static {
        for (int i : BORDER_INDEXES)
            INVENTORY.setItem(i, Stacks.BORDER);

        INVENTORY.setItem(12, RoleType.DPS.getItem());
        INVENTORY.setItem(13, RoleType.TECHNIQUE.getItem());
        INVENTORY.setItem(14, RoleType.TANK.getItem());
    }

    public static Inventory getInventory() {
        return InventoryHelper.cloneInventory(INVENTORY);
    }
}
