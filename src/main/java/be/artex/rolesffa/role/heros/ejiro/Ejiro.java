package be.artex.rolesffa.role.heros.ejiro;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ejiro extends Role {
    @Override
    public String getName() {
        return ChatColor.BLUE + "Ejiro";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .item(new Unbreakable())
                .effect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0))
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.EJIRO;
    }

    @Override
    public Team getCamp() {
        return Team.TANK;
    }

    @Override
    public int getPlacement() {
        return 10;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));

        player.getInventory().addItem(Stacks.UNBREAKABLE);
    }

}
