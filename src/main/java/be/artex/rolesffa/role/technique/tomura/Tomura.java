package be.artex.rolesffa.role.technique.tomura;

import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.role.technique.tomura.mains.Mains;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class Tomura extends Role {
    @Override
    public ItemStack getItemStack() {
        return Stacks.TOMURA;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<ItemHolder> getItems() {
        return Collections.singletonList(new ItemHolder(new Mains()));
    }
}
