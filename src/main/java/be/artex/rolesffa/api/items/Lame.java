package be.artex.rolesffa.api.items;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Lame implements SPItem {

    @Override
    public ItemStack getItemStack() {
        return Stacks.LAME_DE_NICHIRINE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(getItemStack().getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(getItemStack().getItemMeta().getDisplayName() +
                "\n" + ChatColor.GRAY + "Click droit pour avoir une lame aléatiore entre:\n\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.DARK_GRAY + " Lame de Force (+5% de dégats)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.YELLOW + " Lame de Vitesse (+7% de vitesse)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.LIGHT_PURPLE + " Lame de Vie (+2 coeurs)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.GRAY + " Lame de Résistance (+5% de résistance)\n" +
                " " + ChatColor.GRAY + StringUtils.dot + ChatColor.GREEN + " Lame de NoFall\n")
                });

        description.setHoverEvent(event);
        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {

    }
}
