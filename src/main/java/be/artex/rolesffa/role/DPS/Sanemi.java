package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Sanemi extends Role {
    public static final HashMap<UUID, Integer> playerSpeedKills = new HashMap<>();

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .onKill(ChatColor.GRAY + "vous gagnerez " + ChatColor.YELLOW + "7%" + ChatColor.GRAY + " de " + ChatColor.YELLOW + "vitesse")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SANEMI;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public Float getStrength() {
        return 12.5f;
    }

    @Override
    public List<ItemHolder> getItems() {
        return Collections.singletonList(ItemHolder.inHand(new Lame()));
    }

    @Override
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();

        if (playerSpeedKills.get(killer.getUniqueId()) == null) {
            playerSpeedKills.put(killer.getUniqueId(), 1);
        }  else  {
            int kills = playerSpeedKills.get(killer.getUniqueId());
            kills++;

            playerSpeedKills.put(killer.getUniqueId(), kills);
        }

        if (playerSpeedKills.get(killer.getUniqueId()) <= 4) {
            event.getEntity().getKiller().setWalkSpeed((event.getEntity().getKiller().getWalkSpeed() / 100) * 107);
            event.getEntity().getKiller().sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez gagné " + ChatColor.DARK_AQUA + ChatColor.BOLD + "7% de vitesse" + ChatColor.AQUA + ".");
        }
    }
}
