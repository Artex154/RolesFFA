package be.artex.rolesffa.api.stats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Effects {
    private static final Map<UUID, Effects> PLAYERS_STATS = new HashMap<>();

    private final UUID player;
    private double strengthBonus;
    private double resistanceBonus;
    private double speedBonus;

    private Effects(UUID player) {
        this.player = player;
        this.strengthBonus = 0;
        this.resistanceBonus = 0;
        this.speedBonus = 0;
    }

    public static @NotNull Effects get(@NotNull UUID uuid) {
        return PLAYERS_STATS.computeIfAbsent(uuid, Effects::new);
    }

    public double getBonus(@NotNull RFEffectsType stat) {
        switch (stat) {
            case SPEED:
                return speedBonus;
            case RESISTANCE:
                return resistanceBonus;
            case STRENGTH:
                return strengthBonus;
            default:
                return 0;
        }
    }

    public void setBonus(RFEffectsType stat, double value) {
        switch (stat) {
            case SPEED:
                this.speedBonus = value;
                applySpeed(Bukkit.getPlayer(this.player));
                break;
            case RESISTANCE:
                this.resistanceBonus = value;
                break;
            case STRENGTH:
                this.strengthBonus = value;
                break;
        }

        PlayerRFEffectsChangeEvent event = new PlayerRFEffectsChangeEvent(Bukkit.getPlayer(player), stat, value);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void addBonus(RFEffectsType stat, double amount) {
        switch (stat) {
            case SPEED:
                this.speedBonus += amount;
                applySpeed(Bukkit.getPlayer(this.player));
                break;
            case RESISTANCE:
                this.resistanceBonus += amount;
                break;
            case STRENGTH:
                this.strengthBonus += amount;
                break;
        }

        PlayerRFEffectsChangeEvent event = new PlayerRFEffectsChangeEvent(Bukkit.getPlayer(player), stat, amount);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static void remove(@NotNull UUID uuid) {
        PLAYERS_STATS.remove(uuid);
    }

    private void applySpeed(Player player) {
        float totalSpeed = (float) (0.2 * (1 + this.speedBonus / 100.0));

        player.setWalkSpeed(totalSpeed);
    }

}