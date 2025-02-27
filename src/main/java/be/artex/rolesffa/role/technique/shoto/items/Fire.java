package be.artex.rolesffa.role.technique.shoto.items;

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
        TextComponent description = new TextComponent(getItemStack().getItemMeta().getDisplayName());

        String hoverText = getItemStack().getItemMeta().getDisplayName() + "\n\n" +
                Main.dot + ChatColor.GRAY + "En " + ChatColor.RED + "tappant " + ChatColor.GRAY +
                "un joueur, vous " + ChatColor.GOLD + "enflammerez" + ChatColor.GRAY + " le joueur.   \n ";

        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new BaseComponent[]{new TextComponent(hoverText)}
        );

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();

        player.setFireTicks(8*20);
    }
}
