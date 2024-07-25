package be.artex.rolesffa.api.items;

import be.artex.rolesffa.api.SPItem;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.cooldown.Cooldown;
import be.artex.rolesffa.util.Stacks;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ImmenseSpeed extends SPItem {

    @Override
    public ItemStack getItemStack() {
        return Stacks.IMMENSE_SPEED;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(Stacks.IMMENSE_SPEED.getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(
                Stacks.IMMENSE_SPEED.getItemMeta().getDisplayName() + "\n " + ChatColor.GRAY + StringUtils.dot + " En faissant un clique droit vous ferez un " + ChatColor.AQUA + "dash" + ChatColor.GRAY + "." +
                        "\n " + ChatColor.GRAY + StringUtils.dot + " Vous aurez " + ChatColor.GREEN + "NoFall" + ChatColor.GRAY + " pendant " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + " après votre " + ChatColor.AQUA + "dash" + ChatColor.GRAY + "." +
                "\n " + ChatColor.GRAY + StringUtils.dot + " Cooldown: " + ChatColor.YELLOW + "70 secondes"
                )});

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Cooldown cooldown = Cooldown.get("immense_speed");

        if (cooldown.isPlayerInCooldown(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "L'" + ChatColor.BOLD + "Immense Speed" + ChatColor.RED + " est en cooldown.");
            return;
        }

        Vector direction = player.getLocation().getDirection();
        double dashSpeed = 5;

        Vector dash = direction.multiply(dashSpeed);

        double maxVelocity = 4.0;
        double clampedX = Math.min(Math.max(dash.getX(), -maxVelocity), maxVelocity);
        double clampedY = Math.min(Math.max(dash.getY(), -maxVelocity), maxVelocity);
        double clampedZ = Math.min(Math.max(dash.getZ(), -maxVelocity), maxVelocity);

        Vector clampedDash = new Vector(clampedX, clampedY, clampedZ);
        player.setVelocity(clampedDash);

        cooldown.addPlayer(player.getUniqueId(), 70*20L, cooldown);
    }
}
