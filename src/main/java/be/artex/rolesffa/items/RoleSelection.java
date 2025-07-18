package be.artex.rolesffa.items;

import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.gui.ChoiceOfRoleTypeGUI;
import be.artex.rolesffa.itemStacks.items.PreItems;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RoleSelection extends RFItem {
    @Override
    public ItemStack getItem() {
        return PreItems.SLECTION_BOOK.getStack();
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        player.openInventory(ChoiceOfRoleTypeGUI.getInventory());
    }
}
