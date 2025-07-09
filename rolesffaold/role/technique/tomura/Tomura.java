package be.artex.rolesffaold.role.technique.tomura;

import be.artex.rolesffaold.api.ItemHolder;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.role.technique.tomura.mains.Mains;
import be.artex.rolesffaold.Stacks;
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
        return Collections.singletonList(ItemHolder.inHand(new Mains()));
    }
}
