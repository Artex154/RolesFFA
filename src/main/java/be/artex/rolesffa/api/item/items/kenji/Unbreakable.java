package be.artex.rolesffa.api.item.items.kenji;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.cooldown.Cooldown;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Unbreakable extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.UNBREAKABLE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(getItemStack().getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(getItemStack().getItemMeta().getDisplayName() + "\n\n" +
                Main.dot + ChatColor.GRAY + "En faissant un clique, vous " + ChatColor.AQUA + "recevrez" + ChatColor.DARK_GRAY + " weakness 1" + ChatColor.GRAY + " et resistance 2 pendant " + ChatColor.YELLOW + " 7 secondes" + ChatColor.GRAY + ".    \n\n" +
                Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 80 secondes" + ChatColor.GRAY + ".\n"
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
            player.sendMessage(org.bukkit.ChatColor.DARK_AQUA + "[" + org.bukkit.ChatColor.AQUA + org.bukkit.ChatColor.BOLD + "RolesFFA" + org.bukkit.ChatColor.DARK_AQUA + "]" + org.bukkit.ChatColor.AQUA + " " + org.bukkit.ChatColor.BOLD + "Kusarigama" + org.bukkit.ChatColor.AQUA + " est en cooldown pour encore " + org.bukkit.ChatColor.BOLD + cooldown.getTimeLeft(uuid) + org.bukkit.ChatColor.AQUA + ".");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*7, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*7, 0));

        cooldown.addPlayer(uuid, 80*20);
    }
}
