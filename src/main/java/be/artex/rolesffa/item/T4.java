package be.artex.rolesffa.item;

import be.artex.rolesffa.api.item.SPItem;
import be.raft.crafty.item.ItemBuilder;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class T4 extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return new ItemBuilder<>(new ItemStack(Material.DIAMOND_SWORD)).addEnchant(Enchantment.DAMAGE_ALL, 4).build();
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(ChatColor.AQUA + "une épée tranchant 4");
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(ChatColor.AQUA + "Diamond Sword\n" +
                ChatColor.GRAY + "Sharpness IV\n\n" +
                ChatColor.BLUE + "+12 Attack Damage ")});

        description.setHoverEvent(event);

        return description;
    }
}
