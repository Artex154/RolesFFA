package be.artex.rolesffa.api.item;

import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RFItem {
    private static final List<RFItem> items = new ArrayList<>();

    public abstract Item getItem();

    public void onClick(PlayerInteractEvent event) {
    }

    public static List<RFItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static void registerItem(RFItem item) {
        items.add(item);
    }

    public static RFItem getItemFromStack(ItemStack stack) {
        for (RFItem item : items)
            if (item.getItem().getStack().isSimilar(stack))
                return item;

        return null;
    }
}
