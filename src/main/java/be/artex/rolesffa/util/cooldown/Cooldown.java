package be.artex.rolesffa.util.cooldown;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Cooldown {
    private final String id;
    private final ArrayList<UUID> playerCooldowns;
    private static final HashMap<String, Cooldown> cooldowns = new HashMap<>();

    private Cooldown(String id) {
        this.id = id;
        this.playerCooldowns = new ArrayList<>();
    }

    public static Cooldown get(String id) {
        return cooldowns.computeIfAbsent(id, Cooldown::new);
    }

    public void addPlayer(UUID uuid, long time) {
        if (playerCooldowns.contains(uuid))
            return;

        playerCooldowns.add(uuid);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            playerCooldowns.remove(uuid);

            cooldowns.put(id, this);
        }, time);
    }

    public boolean isPlayerInCooldown(UUID uuid) {
        return playerCooldowns.contains(uuid);
    }

    public static void removePlayerFromAllCooldowns(UUID uuid) {
        for (Cooldown cooldown : cooldowns.values()) {
            cooldown.playerCooldowns.remove(uuid);
        }
    }
}
