package be.artex.rolesffaold.role.technique.izuku;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.role.Role;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Izuku extends Role {
    @Override
    public TextComponent getDescription() {
        return null;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.IZUKU;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }
}
