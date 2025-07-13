package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Strength {
    private static final HashMap<Player, Float> PLAYER_STRENGTH = new HashMap<>();

    public static void setPlayerStrength(Player player, float strength) {
        PLAYER_STRENGTH.put(player, strength);
    }

    public static Float getPlayerStrength(Player player) {
        return PLAYER_STRENGTH.getOrDefault(player, 100f);
    }

    public static void resetPlayerStrength(Player player) {
        PLAYER_STRENGTH.put(player, 100f);
    }
}
