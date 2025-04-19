package be.artex.rolesffa.role.technique.kyojuro;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
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
        TextComponent description = new TextComponent(ChatColor.GRAY + "le " + getItemStack().getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(getItemStack().getItemMeta().getDisplayName() + "\n" +
                ChatColor.GRAY + "Flame I")});

        description.setHoverEvent(event);

        return description;
    }
}
