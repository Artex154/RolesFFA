package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kokushibo extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Kokushibo";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .strength(20)
                .effect(new PotionEffect(PotionEffectType.SPEED, 0, 0))
                .onKill("vous gagnerez un " + ChatColor.LIGHT_PURPLE + "coeur permanent")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KOKUSHIBO;
    }

    @Override
    public Team getCamp() {
        return Team.DPS;
    }

    @Override
    public int getPlacement() {
        return 16;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
    }

    @Override
    public void onPlayerKill(PlayerDeathEvent event) {
        event.getEntity().getKiller().setMaxHealth(event.getEntity().getKiller().getMaxHealth() + 2);
    }
}
