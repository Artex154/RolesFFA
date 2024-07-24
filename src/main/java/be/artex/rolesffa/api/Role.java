package be.artex.rolesffa.api;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Role {
    String getName();
    TextComponent getDescription();
    ItemStack getItemStack();
    Team getCamp();
    Team getType();
    int getPlacement();

    void onAssigned(Player player);
}
