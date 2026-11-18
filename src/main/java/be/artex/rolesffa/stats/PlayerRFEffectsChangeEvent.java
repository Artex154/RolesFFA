package be.artex.rolesffa.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRFEffectsChangeEvent extends Event implements Cancellable {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final RFEffectsType effectType;
    private final double amountChanged;
    private boolean isCancelled = false;

    public PlayerRFEffectsChangeEvent(Player player, RFEffectsType effectType, double amountChanged) {
        this.player = player;
        this.effectType = effectType;
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

        Effects effects = Effects.get(player.getUniqueId());

        if (b) {
            effects.addBonus(effectType, -amountChanged);
        } else {
            effects.addBonus(effectType, amountChanged);
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

    public RFEffectsType getEffectType() {
        return effectType;
    }

    public double getAmountChanged() {
        return amountChanged;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
