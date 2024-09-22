package be.artex.rolesffa.api;

import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Role {
    public abstract @NotNull String getName();
    public abstract @NotNull TextComponent getDescription();
    public abstract @NotNull ItemStack getItemStack();
    public abstract @NotNull Team getCamp();
    public abstract int getPlacement();
    public abstract int getStrength();

    public abstract void onAssigned(Player player);

    public void onPlayerKill(PlayerDeathEvent event) {
    }
}
