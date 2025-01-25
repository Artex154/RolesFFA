package be.artex.rolesffa.api.role.roles.ASV;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.item.items.tomura.Mains;
import be.artex.rolesffa.api.role.roles.RoleType;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.api.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Tomura extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Tomura";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(ChatColor.RED + "Tomura")
                .item(new Mains())
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.TOMURA;
    }

    @Override
    public Team getCamp() {
        return Team.SUP_VIL;
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

        player.getInventory().addItem(Stacks.MAINS);
    }
}
