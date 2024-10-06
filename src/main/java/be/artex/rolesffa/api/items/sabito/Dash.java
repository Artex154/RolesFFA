package be.artex.rolesffa.api.items.sabito;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.cooldown.Cooldown;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;

public class Dash extends SPItem {

    public static ArrayList<UUID> playerWithSpeed = new ArrayList<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.SABITO_DASH;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(ChatColor.GRAY + "votre " + Stacks.SABITO_DASH.getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(Stacks.SABITO_DASH.getItemMeta().getDisplayName() + "\n\n" +
                StringUtils.dot + ChatColor.GRAY + "En faissant un clique, vous ferez un " + ChatColor.AQUA + "dash" + ChatColor.GRAY + " en " + ChatColor.AQUA + "avant" + ChatColor.GRAY + ".      \n" +
                StringUtils.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 45 secondes" + ChatColor.GRAY + ".\n"
        )});

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.get("sabito_dash");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (cooldown.isPlayerInCooldown(uuid)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Votre " + ChatColor.BOLD + "Dash" + ChatColor.AQUA + " est en cooldown pour encore " + ChatColor.BOLD + cooldown.getTimeLeft(uuid) + ChatColor.AQUA + ".");
            return;
        }

        Location loc = player.getLocation();
        Vector vec = loc.getDirection();
        player.setVelocity(vec.multiply(2));

        cooldown.addPlayer(uuid, 45*20);

        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*20, 1));

        playerWithSpeed.add(player.getUniqueId());

        Bukkit.getScheduler().runTaskLater(Main.instance, () ->  {
            if (playerWithSpeed.contains(player.getUniqueId()))
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

            playerWithSpeed.remove(player.getUniqueId());
        }, 202);
    }
}
