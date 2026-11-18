package be.artex.rolesffa.roles.technique.machoireTitan;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.strontium.itemBuilder.ItemBuilder;
import be.artex.strontium.player.StrontPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Machoire extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.NETHER_STAR)
            .name(ChatColor.AQUA + "" + ChatColor.BOLD + "Mâchoire")
            .lore(" ",
                    ChatColor.GRAY + " En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un " + ChatColor.AQUA + "joueur " + ChatColor.GRAY + "avec, vous " + ChatColor.RED + "infligerez" + ChatColor.RED + " 2,5❤ " + ChatColor.GRAY + "de " + ChatColor.RED + "dégats" + ChatColor.GRAY + ".      ", ChatColor.GRAY + " Cependant, vous pouvez utiliser que cet item en étant " + ChatColor.AQUA + "transformé" + ChatColor.GRAY + ".       ", " ", ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();

        if (!Role.transformedPlayers.contains(damager)) {
            damager.sendMessage(Message.generate("Vous devez être transformé pour utiliser cet item."));
            return;
        }

        Cooldown cooldown = Cooldown.getCooldown("machoire", Duration.of(10, ChronoUnit.SECONDS), "Votre " + ChatColor.AQUA + ChatColor.BOLD + "Mâchoire");

        if (cooldown.isPlayerInCooldown(damager)) {
            damager.sendMessage(Message.cooldownTimeLeft(cooldown.getPlayerCooldownTimeLeft(damager)));
            return;
        }

        StrontPlayer.getSrontPlayer((Player) event.getEntity()).inflictTrueDamage(5);

        cooldown.putPlayerInCooldown(damager);
    }
}
