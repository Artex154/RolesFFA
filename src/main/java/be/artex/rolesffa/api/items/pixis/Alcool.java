package be.artex.rolesffa.api.items.pixis;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.cooldown.Cooldown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Alcool extends SPItem {

    // todo: create an ItemStack;
    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.GLASS_BOTTLE);
    }

    // todo: create a description for a hover;
    @Override
    public TextComponent getDescription() {
        return null;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("alcool");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " " + ChatColor.BOLD + "Kusarigama" + ChatColor.AQUA + " est en cooldown pour encore " + ChatColor.BOLD + cooldown.getTimeLeft(uuid) + ChatColor.AQUA + ".");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10, 0));

        cooldown.addPlayer(uuid, 45*20L);
    }
}