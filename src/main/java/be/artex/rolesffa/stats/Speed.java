package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Speed {
    private static final HashMap<Player, Float> PLAYER_SPEED = new HashMap<>();

    public static void setPlayerSpeed(Player player, float speed) {
        speed = (0.2f / 100) * speed;

        PLAYER_SPEED.put(player, speed);

        player.setWalkSpeed(speed);
    }

    public static Float getPlayerSpeed(Player player) {
        return PLAYER_SPEED.getOrDefault(player, 100f);
    }

    public static void resetPlayerSpeed(Player player) {
        player.setWalkSpeed(0.2f);

        PLAYER_SPEED.put(player, 100f);
    }
}
