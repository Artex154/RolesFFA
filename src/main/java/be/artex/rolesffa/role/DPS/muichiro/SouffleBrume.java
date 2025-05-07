package be.artex.rolesffa.role.DPS.muichiro;

import be.artex.rolesffa.Cooldown;
import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class SouffleBrume extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.SOUFFLEBRUME;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.getItemDescription(getItemStack(), "le");
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("kusarigama");

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Le" + ChatColor.DARK_AQUA + " Souffle de La Brume" + ChatColor.GRAY + " est en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(uuid) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        int numberOfPlayers = 0;

        for (Entity entity : player.getNearbyEntities(30, 30, 30)) {
            if (!(entity instanceof Player))
                continue;

            numberOfPlayers++;

            ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 0));
            ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5 * 20, 0));
        }

        if (numberOfPlayers != 0)
            cooldown.addPlayer(uuid, 70 * 20);
    }
}
