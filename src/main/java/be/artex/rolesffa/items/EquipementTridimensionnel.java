package be.artex.rolesffa.items;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class EquipementTridimensionnel extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.FISHING_ROD)
            .addEnchant(Enchantment.DURABILITY, 255)
            .itemFlags(ItemFlag.HIDE_ENCHANTS)
            .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Equipement Tridimensionnel")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (Role.transformedPlayers.contains(player)) {
            player.sendMessage(Message.generate("Vous ne pouvez pas utiliser cet item en étant transformé"));
            return;
        }

        Cooldown cooldown = Cooldown.getCooldown("equip_tridi", Duration.of(15, ChronoUnit.SECONDS), "Votre " + ChatColor.GOLD + ChatColor.BOLD + "Equipement Tridimensionnel");

        if (cooldown.isPlayerInCooldown(player)) {
            player.sendMessage(Message.cooldownTimeLeft(cooldown.getPlayerCooldownTimeLeft(player)));
            return;
        }

        FishHook hook = event.getHook();

        if (hook.isDead() || event.getState() == PlayerFishEvent.State.FISHING)
            return;

        Location hookLoc = hook.getLocation();
        Location playerLoc = player.getLocation();

        Vector pull = hookLoc.toVector().subtract(playerLoc.toVector());
        double distance = pull.length() * 5;

        pull.normalize().multiply(Math.min(distance * 0.65, 2.75));
        pull.setY(pull.getY() + 0.25);

        if (pull.getY() > 2.0) {
            pull.setY(2.0);
        }

        player.setVelocity(pull);

        cooldown.putPlayerInCooldown(player);
    }
}
