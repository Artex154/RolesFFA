package be.artex.rolesffa.api.role.roles.LG;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.roles.RoleType;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.listeners.player.playerDamagePlayer.Strength;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.api.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LoupGarou extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Loup Garou";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(ChatColor.RED + "Loup Garou")
                .strength(20)
                .onKill(ChatColor.GRAY + "vous recevrez " + ChatColor.YELLOW + "vitesse 1 " + ChatColor.GRAY + " et " + ChatColor.YELLOW + "4 coeurs d'absorptions " + ChatColor.GRAY + "pendant 1 minute")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.LG;
    }

    @Override
    public Team getCamp() {
        return Team.LG;
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

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        Strength.playerStrength.put(player.getUniqueId(), 12f);
    }

    @Override
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();

        killer.removePotionEffect(PotionEffectType.ABSORPTION);
        killer.removePotionEffect(PotionEffectType.SPEED);

        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60, 0));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60, 1));
    }
}
