package be.artex.rolesffa.items;

import be.artex.rolesffa.api.item.RFItems;
import be.artex.rolesffa.gui.ChoiceOfRoleTypeGUI;
import be.artex.rolesffa.itemStacks.Item;
import be.artex.rolesffa.itemStacks.items.PreItems;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class RoleSelection extends RFItems {
    @Override
    public Item getItem() {
        return PreItems.SLECTION_BOOK;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        player.openInventory(ChoiceOfRoleTypeGUI.getInventory());
    }
}
