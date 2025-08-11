package be.artex.rolesffa.items;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.RoleUtils;
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

public class EquipementTridimensionnelle extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.FISHING_ROD)
            .addEnchant(Enchantment.DURABILITY, 255)
            .itemFlags(ItemFlag.HIDE_ENCHANTS)
            .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Equipement Tridimensionnelle")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (RoleUtils.transformedPlayers.contains(player)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA+ "]" + ChatColor.GRAY + " Vous ne pouvez pas utiliser cet item en étant transformé");
            return;
        }

        Cooldown cooldown = Cooldown.getCooldown("equip_tridi", 15*20L);

        if (cooldown.isPlayerInCooldown(player)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA+ "]" + ChatColor.GRAY + " Vous êtes en cooldown pour encore " + ChatColor.AQUA + cooldown.getPlayerCooldownTimeLeft(player) + " secondes" + ChatColor.GRAY + ".");
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
