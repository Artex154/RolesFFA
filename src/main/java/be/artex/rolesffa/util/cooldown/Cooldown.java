package be.artex.rolesffa.util.cooldown;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Cooldown {
    public final String id;
    private final ArrayList<UUID> playerCooldowns;
    private static final HashMap<String, Cooldown> cooldowns = new HashMap<>();

    private Cooldown(String id) {
        this.id = id;
        this.playerCooldowns = new ArrayList<>();
    }

    public static Cooldown get(String id) {
        if (cooldowns.get(id) == null)
            return new Cooldown(id);

        return cooldowns.get(id);
    }

    public void addPlayer(UUID uuid, long time, Cooldown cooldown) {
        if (playerCooldowns.contains(uuid))
            return;

        cooldowns.put(cooldown.id, cooldown);

        playerCooldowns.add(uuid);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            playerCooldowns.remove(uuid);
            cooldowns.put(cooldown.id, cooldown);
        }, time);
    }

    public boolean isPlayerInCooldown(UUID uuid) {
        return playerCooldowns.contains(uuid);
    }
}
