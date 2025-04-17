package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.listener.player.playerDamagePlayer.Strength;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Knuckles extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Knuckles";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .strength(15f)
                .onHit("vous avez " + ChatColor.AQUA + "15% de chance" + ChatColor.GRAY + " de mettre en " + ChatColor.GOLD + "feu")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KNUCKLES;
    }

    @Override
    public Team getCamp() {
        return Team.DPS;
    }

    @Override
    public int getPlacement() {
        return 15;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));

        Strength.playerStrength.put(player.getUniqueId(), 11.5f);
    }

    @Override
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Random random = new Random();

        if (random.nextInt(20) >= 3) {
            event.getEntity().setFireTicks(20*10);
        }
    }
}
