package be.artex.rolesffa.role.heros.kenji;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.role.RoleType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kenji extends Role {
    @Override
    public String getName() {
        return ChatColor.GREEN + "Kenji";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(ChatColor.GREEN + "Kenji")
                .item(new Unbreakable())
                .effect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0))
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KENJI;
    }

    @Override
    public Team getCamp() {
        return Team.HEROS;
    }

    @Override
    public RoleType getType() {
        return RoleType.RESISTANCE;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));

        player.getInventory().addItem(Stacks.UNBREAKABLE);
    }
}
