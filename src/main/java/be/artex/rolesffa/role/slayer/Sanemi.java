package be.artex.rolesffa.role.slayer;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.role.slayer.item.lame.Lame;
import be.artex.rolesffa.role.RoleType;
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
        return ChatColor.GREEN + "Sanemi";
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(Main.line);
        description.addExtra("\n" + Main.dot + ChatColor.GRAY + " Rôle: " + getName());
        description.addExtra("\n");
        description.addExtra("\n" + Main.dot + ChatColor.GRAY + " Vous possédez " + ChatColor.RED + "+25% de force " + ChatColor.GRAY + "de façon permanente.");
        description.addExtra("\n");
        description.addExtra("\n" + Main.dot + ChatColor.GRAY + " Quand vous tuez un " + ChatColor.AQUA + "joueur" + ChatColor.GRAY + ", vous gagnerez " + ChatColor.YELLOW + "7% de vitesse" + ChatColor.GRAY + " supplémentaire. (cap à 28%)");
        description.addExtra("\n");
        description.addExtra("\n" + Main.dot + ChatColor.GRAY + " Vous possédez une ");
        description.addExtra(new Lame().getDescription());
        description.addExtra(ChatColor.GRAY + ".");
        description.addExtra(Main.line);

        return description;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SANEMI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public RoleType getType() {
        return RoleType.STRENGTH;
    }

    @Override
    public int getPlacement() {
        return 20;
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
