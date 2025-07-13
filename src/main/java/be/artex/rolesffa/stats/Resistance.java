package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Resistance {
    private static final HashMap<Player, Float> PLAYER_RESISTANCE = new HashMap<>();

    public static void setPlayerResistance(Player player, float resistance) {
        PLAYER_RESISTANCE.put(player, resistance);
    }

    public static Float getPlayerResistance(Player player) {
        return PLAYER_RESISTANCE.getOrDefault(player, 100f);
    }

    public static void resetPlayerResistance(Player player) {
        PLAYER_RESISTANCE.put(player, 100f);
    }
}
