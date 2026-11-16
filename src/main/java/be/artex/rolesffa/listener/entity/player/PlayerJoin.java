package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.ScoreboardManager;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.Map;

public class PlayerJoin implements Listener {
    private static final Map<Player, FastBoard> BOARDS = new HashMap<>();
    
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();

        inventory.clear();
        inventory.setArmorContents(null);

        inventory.setItem(4, Stacks.ROLE_SELECTION_BOOK);

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        player.setGameMode(GameMode.ADVENTURE);

        player.teleport(new Location(Bukkit.getWorlds().get(0), 0, 122, 0));

        CoupParalysant.FROZEN_PLAYERS.remove(player);
        
        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.AQUA + "" + ChatColor.BOLD + "Roless FFA");
        ScoreboardManager.updateScoreboard(player, board);

        BOARDS.put(player, board);

        ScoreboardManager.updateHeaderFooter(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        BOARDS.remove(event.getPlayer());
    }
}
