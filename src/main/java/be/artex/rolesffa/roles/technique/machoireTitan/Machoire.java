package be.artex.rolesffa.roles.technique.machoireTitan;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.RoleUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Machoire extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.NETHER_STAR)
            .name(ChatColor.AQUA + "" + ChatColor.BOLD + "Mâchoire")
            .lore(" ",
                    ChatColor.GRAY + " En " + ChatColor.RED + "tapant " + ChatColor.GRAY + "un " + ChatColor.AQUA + "joueur " + ChatColor.GRAY + "avec, vous " + ChatColor.RED + "infligerez" + ChatColor.LIGHT_PURPLE + " 2,5 coeurs " + ChatColor.GRAY + "de " + ChatColor.RED + "dégats" + ChatColor.GRAY + ".      ", ChatColor.GRAY + " Cependant, vous pouvez utiliser que cet item en étant " + ChatColor.AQUA + "transformé" + ChatColor.GRAY + ".       ", " ", ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();

        if (!RoleUtils.transformedPlayers.contains(damager)) {
            damager.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA+ "]" + ChatColor.GRAY + " Vous devez être transformé pour utiliser cet item.");
            return;
        }

        Cooldown cooldown = Cooldown.getCooldown("machoire", 10*20L);

        if (cooldown.isPlayerInCooldown(damager)) {
            damager.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.GRAY + " Vous êtes en cooldown pour encore " + ChatColor.AQUA + cooldown.getPlayerCooldownTimeLeft(damager) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        Player entity = (Player) event.getEntity();

        cooldown.putPlayerInCooldown(damager);

        if ((entity.getHealth() - 5) <= 0)
            entity.setHealth(1);
        else
            entity.setHealth(entity.getHealth() - 5);
    }
}
