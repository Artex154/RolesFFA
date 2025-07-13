package be.artex.rolesffa.gui;

import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.InventoryHelper;
import be.artex.rolesffa.itemStacks.items.GuiItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ChoiceOfRoleTypeGUI {
    private final static Inventory inventory = Bukkit.createInventory(null, 27, "Types de Rôles");

    private final static int[] borderIndex =
            {0, 1, 7, 8,
            9, 17,
            18, 19, 25, 26};

    public static void generateInventory() {
        for (int i : borderIndex) {
            inventory.setItem(i, GuiItems.BORDER.getStack());
        }

        inventory.setItem(12, RoleType.DPS.getItem());
        inventory.setItem(13, RoleType.TECHNIQUE.getItem());
        inventory.setItem(14, RoleType.TANK.getItem());
    }

    public static Inventory getInventory() {
        return InventoryHelper.cloneInventory(inventory);
    }


}
