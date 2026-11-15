package be.artex.rolesffa.api;

import be.artex.rolesffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cooldown {
    private final Duration cooldownTime;
    private final String name;
    private final ArrayList<Player> playerCooldowns;
    private final Map<Player, Long> cooldownStartTimes;

    private static final Map<String, Cooldown> cooldowns = new HashMap<>();

    private Cooldown(Duration cooldownTime, String name) {
        this.cooldownTime = cooldownTime;
        this.name = name;
        this.playerCooldowns = new ArrayList<>();
        this.cooldownStartTimes = new HashMap<>();
    }

    public static @NotNull Cooldown getCooldown(String cooldownID, Duration cooldownTime) {
        return cooldowns.computeIfAbsent(cooldownID, id -> new Cooldown(cooldownTime, "<N/A>"));
    }

    public static @NotNull Cooldown getCooldown(String cooldownID, Duration cooldownTime, String name) {
        return cooldowns.computeIfAbsent(cooldownID, id -> new Cooldown(cooldownTime, name));
    }

    public long getPlayerCooldownTimeLeft(Player player) {
        if (!isPlayerInCooldown(player))
            return 0;

        long startTime = cooldownStartTimes.getOrDefault(player, 0L);
        long elapsedMillis = System.currentTimeMillis() - startTime;

        long cooldownMillis = cooldownTime.toMillis();

        long remainingMillis = cooldownMillis - elapsedMillis;

        return Math.max(0, remainingMillis / 1000);
    }

    public boolean isPlayerInCooldown(Player player) {
        return playerCooldowns.contains(player);
    }

    public static void clearAllCooldowns(Player player) {
        for (Cooldown cooldown : cooldowns.values()) {
            cooldown.playerCooldowns.remove(player);
            cooldown.cooldownStartTimes.remove(player);
        }
    }

    public void putPlayerInCooldown(Player player) {
        if (isPlayerInCooldown(player))
            return;

        long currentStartTime = System.currentTimeMillis();

        playerCooldowns.add(player);
        cooldownStartTimes.put(player, currentStartTime);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (currentStartTime != cooldownStartTimes.get(player))
                return;

            player.sendMessage(Message.generate(this.name + ChatColor.GRAY + " n'est plus en cooldown."));

            playerCooldowns.remove(player);
            cooldownStartTimes.remove(player);
        }, cooldownTime.getSeconds() * 20L);
    }

    public void putPlayerInCooldownWithoutEndMessage(Player player) {
        if (isPlayerInCooldown(player))
            return;

        long currentStartTime = System.currentTimeMillis();

        playerCooldowns.add(player);
        cooldownStartTimes.put(player, currentStartTime);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (currentStartTime != cooldownStartTimes.get(player))
                return;

            playerCooldowns.remove(player);
            cooldownStartTimes.remove(player);
        }, cooldownTime.getSeconds() * 20L);
    }

    public void removePlayerCooldown(Player player) {
        playerCooldowns.remove(player);
        cooldownStartTimes.remove(player);
    }
}
