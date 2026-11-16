package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnStatsChangeEvent extends Event implements Cancellable {
    private final Player player;
    private final StatValues statValue;
    private final int amountChanged;
    private boolean isCancelled = false;

    public OnStatsChangeEvent(Player player, StatValues statValues, int amountChanged) {
        this.player = player;
        this.statValue = statValues;
        this.amountChanged = amountChanged;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        if (isCancelled == b)
            return;

        Stats stats = Stats.get(player.getUniqueId());

        if (b) {
            stats.addBonus(statValue, -amountChanged);
        } else {
            stats.addBonus(statValue, amountChanged);
        }

        isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public StatValues getStatValue() {
        return statValue;
    }

    public int getAmountChanged() {
        return amountChanged;
    }
}
