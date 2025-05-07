package be.artex.rolesffa.role.tank.gyomei;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.role.technique.shoto.Ice;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Kusarigama extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.KUSARIGAMA;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("kusarigama");

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.AQUA + "Kusarigama" + ChatColor.GRAY + " est en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(uuid) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        if (Ice.playersInIce.contains(player.getUniqueId()))
            return;

        int numberOfPlayers = 0;

        for (Entity entity : player.getNearbyEntities(30, 30, 30)) {
            if (!(entity instanceof Player))
                continue;

            numberOfPlayers++;

            entity.teleport(player.getLocation());
        }

        if (numberOfPlayers != 0)
            cooldown.addPlayer(uuid, 90 * 20);
    }
}
