package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Strength {
    private static final HashMap<Player, Integer> PLAYER_STRENGTH = new HashMap<>();

    public static void setPlayerStrength(Player player, float strength) {
        // WTF is this shit
        // TODO: Don't use magic numbers (like 0.2f) use a constant.
        strength = 0.2f * (strength / 100f);
        int percentage = Math.round((strength / 0.2f) * 100);

        PLAYER_STRENGTH.put(player, percentage);

        // TODO: Why does strength affect player speed?
        player.setWalkSpeed(strength);
    }

    public static int getPlayerStrength(Player player) {
        return PLAYER_STRENGTH.getOrDefault(player, 100);
    }

    public static void resetPlayerStrength(Player player) {
        PLAYER_STRENGTH.put(player, 100);
    }
}
