package be.artex.rolesffa.api.role;

import be.artex.rolesffa.api.item.RFItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class Role {
    public static final HashMap<Player, Role> playerRole = new HashMap<>();
    public static final List<Player> transformedPlayers = new ArrayList<>();

    public abstract String getName();
    public abstract String getIdentifier();
    public abstract ItemStack getStack();
    public abstract RoleType getType();
    public abstract TextComponent getDescription();

    public String getSBName() {
        return getName();
    }

    public boolean shouldTick() {
        return false;
    }

    public void tick(Player player) {
    }

    public int getMaxHealth() {
        return 20;
    }

    public List<RFItem> getItems() {
        return Collections.emptyList();
    }

    public int getBonusSpeed() {
        return 0;
    }

    public int getBonusStrength() {
        return 0;
    }

    public int getBonusResistance() {
        return 0;
    }

    public void onPlayerKill(Player killer, Player player) {
    }

    public void onHit(Player player, Player damager) {
    }

    public void whenHit(Player player, Player damager) {
    }

    public void onFlightToggle(PlayerToggleFlightEvent event) {
    }

    // called to get the amount of bonus strength when hitting that player
    // damager is the one with the role
    public int getBonusStrengthAgainst(Player player, Player damager) {
        return 0;
    }

    // called to get the amount of bonus resistance when getting hit by that player.
    // player is the one with the role
    public int getBonusResistanceAgainst(Player player, Player damager) {
        return 0;
    }

    @Nullable
    public static RoleType getRoleTypeFromItem(@NotNull ItemStack stack) {
        for (RoleType type : RoleType.values())
            if (type.getItem().isSimilar(stack))
                return type;

        return null;
    }

    @Nullable
    public static Role getPlayerRole(@NotNull Player player) {
        return playerRole.getOrDefault(player, null);
    }

    public static void removePlayerRole(@NotNull Player player) {
        playerRole.remove(player);
    }

    public static void setPlayerRole(@NotNull Player player, @NotNull Role role) {
        playerRole.put(player, role);
    }
}
