package be.artex.rolesffa.api.item;

import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SPItem {
    public static List<SPItem> registeredItems = new ArrayList<>();

    public abstract @NotNull ItemStack getItemStack();

    public void onClick(PlayerInteractEvent event) {
    }

    public void onHit(EntityDamageByEntityEvent event) {
    }

    public @NotNull TextComponent getDescription() {
       return createItemDescription(getItemStack());
    }

    public static TextComponent createItemDescription(ItemStack stack) {
        return createItemDescription(stack, null);
    }

    public static TextComponent createItemDescription(ItemStack stack, String determiner) {
        if (stack == null || !stack.hasItemMeta())
            return new TextComponent(ChatColor.GRAY + "<null>");

        ItemMeta meta = stack.getItemMeta();
        String name = meta.hasDisplayName() ? meta.getDisplayName() : ChatColor.GRAY + "<null>";

        TextComponent description = new TextComponent(determiner != null ? ChatColor.GRAY + determiner + " " + name : name);

        List<String> lore = meta.hasLore() ? meta.getLore() : Collections.emptyList();

        if (lore.isEmpty())
            return description;

        TextComponent hover = new TextComponent(name);

        for (String str : lore) {
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
