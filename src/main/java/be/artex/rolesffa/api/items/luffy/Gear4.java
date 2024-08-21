package be.artex.rolesffa.api.items.luffy;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.cooldown.Cooldown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Gear4 extends SPItem {

    // todo: Create ItemStack;
    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.NETHER_STAR);
    }

    // todo: Create a Hover
    @Override
    public TextComponent getDescription() {
        return new TextComponent("a");
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("gear_4");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " " + ChatColor.BOLD + "Kusarigama" + ChatColor.AQUA + " est en cooldown pour encore " + ChatColor.BOLD + cooldown.getTimeLeft(uuid) + ChatColor.AQUA + ".");
            return;
        }

        player.setMaxHealth(player.getMaxHealth() + 4);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 0));

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1));
            player.setMaxHealth(player.getMaxHealth() - 4);
        }, 400);

        cooldown.addPlayer(uuid, 80*20);
    }
}
