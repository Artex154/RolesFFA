package be.artex.rolesffa.role.DPS.obanai;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.Cooldown;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
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
        TextComponent description = new TextComponent(ChatColor.GRAY + "le " + Stacks.SOUFFLE.getItemMeta().getDisplayName());
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(Stacks.SOUFFLE.getItemMeta().getDisplayName() + "\n\n" +
                Main.dot + ChatColor.GRAY + "En tappant quelqu'un, vous lui infligerez " + ChatColor.GREEN + "poison 2" + ChatColor.GRAY + " pendant " + ChatColor.GREEN + "5 secondes " + ChatColor.GRAY + ".      \n" +
                Main.dot + ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " 40 secondes" + ChatColor.GRAY + ".\n"
        )});

        description.setHoverEvent(event);

        return description;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Cooldown cooldown = Cooldown.get("souffle_serpent");
        Player damager = (Player) event.getDamager();
        UUID damagerUUID = damager.getUniqueId();

        if (cooldown.isPlayerInCooldown(damagerUUID)) {
            damager.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Le " + ChatColor.BOLD + "Souffle du Serpent" + ChatColor.AQUA + " est en cooldown pour encore " + ChatColor.BOLD + cooldown.getTimeLeft(damagerUUID) + ChatColor.AQUA + ".");
            return;
        }

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5*20, 1));

        cooldown.addPlayer(damagerUUID, 40*20L);
    }
}
