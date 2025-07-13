package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Resistance {
    private static final HashMap<Player, Integer> PLAYER_RESISTANCE = new HashMap<>();

    public static void setPlayerResistance(Player player, float resistance) {
        resistance = 0.2f * (resistance / 100f);

        int percentage = Math.round((resistance / 0.2f) * 100);

        PLAYER_RESISTANCE.put(player, percentage);

        player.setWalkSpeed(resistance);
    }

    public static int getPlayerResistance(Player player) {
        return PLAYER_RESISTANCE.getOrDefault(player, 100);
    }

    public static void resetPlayerResistance(Player player) {
        PLAYER_RESISTANCE.put(player, 100);
    }
}
