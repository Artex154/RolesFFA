package be.artex.rolesffaold.role.technique.kyojuro;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.item.SPItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class SouffleFeu extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.SOUFFLEFEU;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(ChatColor.GRAY + "un arc " + ChatColor.GOLD + "flame I");
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(ChatColor.GOLD + "Arc" + "\n" +
                ChatColor.GRAY + "Flame I\n" +
                ChatColor.GRAY + "Power III")});

        description.setHoverEvent(event);

        return description;
    }
}
