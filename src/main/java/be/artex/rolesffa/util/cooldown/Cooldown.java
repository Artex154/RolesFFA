package be.artex.rolesffa.util.cooldown;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {
    private final String id;
    private final Map<UUID, Long> playerCooldowns; // Map storing expiration time in milliseconds
    private static final HashMap<String, Cooldown> cooldowns = new HashMap<>();

    private Cooldown(String id) {
        this.id = id;
        this.playerCooldowns = new HashMap<>();
    }

    public static Cooldown get(String id) {
        return cooldowns.computeIfAbsent(id, Cooldown::new);
    }

    public void addPlayer(UUID uuid, long ticks) {
        if (playerCooldowns.containsKey(uuid)) return;

        long expiryTime = System.currentTimeMillis() + (ticks * 50); // Convert ticks to milliseconds
        playerCooldowns.put(uuid, expiryTime);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            playerCooldowns.remove(uuid);
            cooldowns.put(id, this);
        }, ticks);
    }

    public boolean isPlayerInCooldown(UUID uuid) {
        return playerCooldowns.containsKey(uuid);
    }

    public long getTimeLeft(UUID uuid) {
        Long expiryTime = playerCooldowns.get(uuid);
        if (expiryTime == null) {
            return 0; // No cooldown
        }

        long timeLeft = expiryTime - System.currentTimeMillis();

        if (timeLeft <= 0) {
            playerCooldowns.remove(uuid); // Remove expired cooldown
            return 0;
        }

        return timeLeft / 1000; // Return time left in seconds
    }

    public static void removePlayerFromAllCooldowns(UUID uuid) {
        for (Cooldown cooldown : cooldowns.values()) {
            cooldown.playerCooldowns.remove(uuid);
        }
    }
}