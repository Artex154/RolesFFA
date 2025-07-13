package be.artex.rolesffa.api.item;

import be.artex.rolesffa.itemStacks.Item;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RFItems {
    private static final List<RFItems> items = new ArrayList<>();

    public abstract Item getItem();

    public void onClick(PlayerInteractEvent event) {
    }

    public static List<RFItems> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static void registerItem(RFItems item) {
        items.add(item);
    }

    public static RFItems getItemFromStack(ItemStack stack) {
        for (RFItems item : items)
            if (item.getItem().getStack().isSimilar(stack))
                return item;

        return null;
    }
}
