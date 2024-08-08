package be.artex.rolesffa.api.items;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.cooldown.Cooldown;
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
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(Stacks.KUSARIGAMA.getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(Stacks.KUSARIGAMA.getItemMeta().getDisplayName() + "\n\n" +
                StringUtils.dot + ChatColor.GRAY + "En faissant un clique, vous téléporterez tout les " + ChatColor.AQUA + "joueurs" + ChatColor.GRAY + " dans un rayon de 50 blocs sur vous.     \n" +
                StringUtils.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 70 secondes" + ChatColor.GRAY + ".\n"
        )});

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("kusarigama");

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " " + ChatColor.BOLD + "Kusarigama" + ChatColor.AQUA + " est en cooldown.");
            return;
        }

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
