package be.artex.rolesffaold.role.technique.shoto;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.item.SPItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Fire extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.FIRE;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.createItemDescription(getItemStack(), "votre");
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();

        player.setFireTicks(8*20);
    }
}
