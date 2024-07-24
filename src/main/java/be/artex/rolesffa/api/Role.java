package be.artex.rolesffa.api;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Role {
    public abstract String getName();
    public abstract TextComponent getDescription();
    public abstract ItemStack getItemStack();
    public abstract Team getCamp();
    public abstract Team getType();
    public abstract int getPlacement();

    public abstract void onAssigned(Player player);
}
