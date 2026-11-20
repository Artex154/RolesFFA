package be.artex.rolesffa.roles.technique.machoireTitan.items;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.roles.technique.machoireTitan.TitanMachoire;
import be.artex.rolesffa.roles.technique.machoireTitan.TransformationState;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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
        Role role = Role.getPlayerRole(player);

        if (!(role instanceof TitanMachoire)) {
            player.setItemInHand(new ItemStack(Material.AIR));
            player.updateInventory();

            return;
        }

        TitanMachoire r = (TitanMachoire) role;

        if (r.transformationState == TransformationState.TRANSFORMED) {
            player.sendMessage(Message.generate("Vous devez être non-transformé pour utilser votre équipement tridimensionel!"));
            return;
        }

        if (r.tridiCDT != 0) {
            player.sendMessage(Message.cooldownTimeLeft(r.tridiCDT / 20));
            return;
        }

        if (r.hook == null || r.hook != event.getHook()) {
            r.hook = event.getHook();
            return;
        }

        r.hook = null;

        r.tridiCDT = 15 * 20L;

        if (event.getHook().isDead() || event.getState() == PlayerFishEvent.State.FISHING)
            return;

        Location hookLoc = event.getHook().getLocation();
        Location playerLoc = player.getLocation();

        Vector pull = hookLoc.toVector().subtract(playerLoc.toVector());
        double distance = pull.length() * 5;

        pull.normalize().multiply(Math.min(distance * 0.65, 2.75));
        pull.setY(pull.getY() + 0.25);

        if (pull.getY() > 2.0)
            pull.setY(2.0);

        player.setVelocity(pull);
    }
}
