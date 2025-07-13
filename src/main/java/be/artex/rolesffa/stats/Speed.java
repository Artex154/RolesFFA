package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Speed {
    private static final HashMap<Player, Integer> PLAYER_SPEED = new HashMap<>();

    public static void setPlayerSpeed(Player player, float speed) {
        speed = 0.2f * (speed / 100f);

        int percentage = Math.round((speed / 0.2f) * 100);

        PLAYER_SPEED.put(player, percentage);

        player.setWalkSpeed(speed);
    }

    public static int getPlayerSpeed(Player player) {
        return PLAYER_SPEED.getOrDefault(player, 100);
    }

    public static void resetPlayerSpeed(Player player) {
        player.setWalkSpeed(0.2f);

        PLAYER_SPEED.put(player, 100);
    }
}
