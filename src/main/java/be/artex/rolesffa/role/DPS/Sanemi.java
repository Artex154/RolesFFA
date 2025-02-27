package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.listener.player.playerDamagePlayer.Strength;
import be.artex.rolesffa.api.role.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class Sanemi extends Role {

    public static final HashMap<UUID, Integer> playerSpeedKills = new HashMap<>();

    @Override
    public String getName() {
        return ChatColor.RED + "Sanemi";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .strength(25)
                .item(new Lame())
                .onHit(ChatColor.GRAY + "vous gagnerez " + ChatColor.YELLOW + "7%" + ChatColor.GRAY + " de " + ChatColor.YELLOW + "vitesse")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SANEMI;
    }

    @Override
    public Team getCamp() {
        return Team.DPS;
    }

    @Override
    public int getPlacement() {
        return 13;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        Strength.playerStrength.put(player.getUniqueId(), 12.5f);
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
