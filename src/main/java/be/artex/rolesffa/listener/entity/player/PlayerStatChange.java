package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.ScoreboardManager;
import be.artex.rolesffa.stats.PlayerRFEffectsChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerStatChange implements Listener {
    @EventHandler
    public void onPlayerStatsChange(PlayerRFEffectsChangeEvent event) {
        ScoreboardManager.updateHeaderFooter(event.getPlayer());
    }
}
