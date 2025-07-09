package be.artex.rolesffaold.role.technique.tomura.mains;

import be.artex.rolesffaold.Main;
import be.artex.rolesffaold.api.item.SPItem;
import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.Cooldown;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class Mains extends SPItem {
    public static final HashMap<UUID, MainsHolder> playerLosedItems = new HashMap<>();

    @Override
    public ItemStack getItemStack() {
        return Stacks.MAINS;
    }

    @Override
    public TextComponent getDescription() {
        return SPItem.createItemDescription(getItemStack(), "vos");
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Cooldown cooldown = Cooldown.get("tomura_hands");
        Player damager = (Player) event.getDamager();
        UUID damagerUUID = damager.getUniqueId();

        if (cooldown.isPlayerInCooldown(damagerUUID)) {
            damager.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " Vos Mains sont en cooldown pour encore " + ChatColor.YELLOW + cooldown.getTimeLeft(damagerUUID) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        playerLosedItems.put(player.getUniqueId(), new MainsHolder(player.getItemInHand(), player.getInventory().getHeldItemSlot()));

        player.setItemInHand(Stacks.UNUSABLE);

        cooldown.addPlayer(damagerUUID, 40*20L);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (playerLosedItems.get(player.getUniqueId()) != null) {
                MainsHolder holder = playerLosedItems.get(player.getUniqueId());
                player.getInventory().setItem(holder.getPlacement(), holder.getStack());
                playerLosedItems.put(player.getUniqueId(), null);
            }
        }, 5*20L);
    }
}
