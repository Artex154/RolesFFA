package be.artex.rolesffa.api.roles.LG;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.Strength;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.builder.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VPL extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Vilain Petit Loup";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(ChatColor.RED + "Vilain Petit Loup")
                .effect(new PotionEffect(PotionEffectType.SPEED, 20, 0))
                .strength(15)
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.VPL;
    }

    @Override
    public Team getCamp() {
        return Team.LG;
    }

    @Override
    public int getPlacement() {
        return 21;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));

        Strength.playerStrength.put(player.getUniqueId(), 11.5f);
    }
}
