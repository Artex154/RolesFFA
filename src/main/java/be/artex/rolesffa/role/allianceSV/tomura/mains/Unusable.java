package be.artex.rolesffa.role.allianceSV.tomura.mains;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.Stacks;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Unusable extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.UNUSABLE;
    }

    @Override
    public TextComponent getDescription() {
        return null;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Tomura " + ChatColor.AQUA + "vous a désactivé cette item.");

        event.setCancelled(true);
    }
}
