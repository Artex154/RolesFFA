package be.artex.rolesffa.api.role.roles.slayer;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.item.items.gyomei.Kusarigama;
import be.artex.rolesffa.api.item.items.slayer.lame.Lame;
import be.artex.rolesffa.api.role.roles.RoleType;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.api.builder.description.DescriptionBuilder;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Gyomei extends Role {
    @Override
    public String getName() {
        return ChatColor.GREEN + "Gyomei";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .item(new Kusarigama(), new Lame())
                .effect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 0))
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.GYOMEI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public RoleType getType() {
        return RoleType.RESISTANCE;
    }

    @Override
    public int getPlacement() {
        return 21;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        player.getInventory().addItem(Stacks.KUSARIGAMA);
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
    }
}
