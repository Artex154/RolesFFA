package be.artex.rolesffa.role.DPS.obanai;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.Cooldown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Souffle extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.SOUFFLE;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.createItemDescription(getItemStack(), "le");
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Cooldown cooldown = Cooldown.get("souffle_serpent");
        Player damager = (Player) event.getDamager();
        UUID damagerUUID = damager.getUniqueId();

        if (cooldown.isPlayerInCooldown(damagerUUID)) {
            damager.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Le " + ChatColor.GREEN + "Souffle du Serpent" + ChatColor.GRAY + " est en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(damagerUUID) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5*20, 1));

        cooldown.addPlayer(damagerUUID, 40*20L);
    }
}
