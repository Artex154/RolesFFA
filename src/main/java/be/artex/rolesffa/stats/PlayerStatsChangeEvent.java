package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerStatsChangeEvent extends Event implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final StatValues statValue;
    private final double amountChanged;
    private boolean isCancelled = false;

    public PlayerStatsChangeEvent(Player player, StatValues statValues, double amountChanged) {
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
        return HANDLER_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public StatValues getStatValue() {
        return statValue;
    }

    public double getAmountChanged() {
        return amountChanged;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
