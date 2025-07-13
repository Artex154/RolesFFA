package be.artex.rolesffa.helper;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryHelper {
    public static Inventory cloneInventory(Inventory inventory) {
        Inventory inv = Bukkit.createInventory(inventory.getHolder(), inventory.getSize(), inventory.getTitle());

        ItemStack[] originalContents = inventory.getContents();
        ItemStack[] copiedContents = new ItemStack[originalContents.length];

        for (int i = 0; i < originalContents.length; i++)
            if (originalContents[i] != null)
                copiedContents[i] = originalContents[i].clone();

        inv.setContents(copiedContents);

        return inv;
    }
}
