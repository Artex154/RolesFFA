package be.artex.rolesffa.role.technique.shoto;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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
        return SPItem.getItemDescription(getItemStack(), "votre");
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();

        player.setFireTicks(8*20);
    }
}
