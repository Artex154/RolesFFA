package be.artex.rolesffa.util;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.UUID;

public class Cooldown {
    private static String id;
    private static ArrayList<UUID> cooldowns;

    public Cooldown(String id, ArrayList<UUID> cooldowns) {
        this.id = id;
        this.cooldowns = cooldowns;
    }

    public static Cooldown create(String id) {
        return new Cooldown(id, new ArrayList<>());
    }

    public void addPlayer(UUID uuid, CooldownType type) {
        cooldowns.add(uuid);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> cooldowns.remove(uuid), type.getTime());
    }

    public boolean isPlayerInCooldown(UUID uuid) {
        return cooldowns.contains(uuid);
    }

    public static String getId() {
        return id;
    }
}
