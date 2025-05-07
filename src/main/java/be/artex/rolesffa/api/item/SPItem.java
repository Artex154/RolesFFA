package be.artex.rolesffa.api.item;

import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class SPItem {
    public static ArrayList<SPItem> registeredItems = new ArrayList<>();

    public abstract @NotNull ItemStack getItemStack();

    public void onClick(PlayerInteractEvent event) {
    }

    public void onHit(EntityDamageByEntityEvent event) {
    }

    public @NotNull TextComponent getDescription() {
       return getItemDescription(getItemStack());
    }

    public static TextComponent getItemDescription(ItemStack stack) {
        String stackName = stack.getItemMeta().getDisplayName();

        TextComponent description = new TextComponent(stackName);

        if (!stack.getItemMeta().hasLore())
            return description;

        TextComponent hover = new TextComponent(stackName);

        for (String str : stack.getItemMeta().getLore()) {
            hover.addExtra("\n");
            hover.addExtra(str);
        }

        description.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{hover}));

        return description;
    }

    public static TextComponent getItemDescription(ItemStack stack, String determiner) {
        TextComponent description = new TextComponent(ChatColor.GRAY + determiner + " " + stack.getItemMeta().getDisplayName());

        if (!stack.getItemMeta().hasLore())
            return description;

        TextComponent hover = new TextComponent(stack.getItemMeta().getDisplayName());

        for (String str : stack.getItemMeta().getLore()) {
            hover.addExtra("\n");
            hover.addExtra(str);
        }

        description.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{hover}));

        return description;
    }

    public static void registerItem(SPItem item) {
        registeredItems.add(item);
    }

}
