package be.artex.rolesffa.listener.entity.player;

import be.artex.rolesffa.ScoreboardManager;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.strontium.holder.Holder;
import be.artex.strontium.player.StrontPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDeath implements Listener {
    private final DecimalFormat DF = new DecimalFormat("#.##");

    public static final Map<UUID, PlayerStatsHolder> PLAYER_STATS = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Role playerRole = RoleUtils.getPlayerRole(player);

        event.getDrops().clear();
        event.getDrops().add(new ItemStack(Material.LAVA_BUCKET));
        event.getDrops().add(new ItemStack(Material.WATER_BUCKET));
        event.getDrops().add(new ItemStack(Material.LEAVES, 64));
        event.getDrops().add(new ItemStack(Material.ARROW, 24));

        PlayerStatsHolder playersStats = PLAYER_STATS.computeIfAbsent(player.getUniqueId(), ignored -> new PlayerStatsHolder());

        playersStats.addDeath();

        if (playerRole == null) {
            ScoreboardManager.updateScoreboard(player);
            return;
        }

        RoleUtils.setPlayerRole(player, null);
        ScoreboardManager.updateScoreboard(player);

        if (player.getKiller() == null) {
            event.setDeathMessage(ChatColor.RED + " " + ChatColor.BOLD + "MORT! " + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " (" + playerRole.getType().getColor() + playerRole.getName() + ChatColor.GRAY + ") est mort(e).");
            return;
        }

        Player killer = player.getKiller();
        StrontPlayer strontKiller = StrontPlayer.getSrontPlayer(killer);

        PlayerStatsHolder killersStats = PLAYER_STATS.computeIfAbsent(killer.getUniqueId(), ignored -> new PlayerStatsHolder());
        killersStats.addKill();

        Holder<Double, ChatColor> health = new Holder<>((killer.getHealth() + strontKiller.getAbsorptionHealth()) / 2, ChatColor.RED);

        health.set(0, killer.getHealth() + strontKiller.getAbsorptionHealth());

        if (strontKiller.getAbsorptionHealth() > 0)
            health.set(1, ChatColor.YELLOW);

        event.setDeathMessage(ChatColor.RED + " " + ChatColor.BOLD + "MORT! " + ChatColor.GREEN + player.getName() + ChatColor.GRAY + " (" + playerRole.getType().getColor() + playerRole.getName() + ChatColor.GRAY + ") a été tué(e) par " + ChatColor.RED + killer.getName() + ChatColor.GRAY + " [" + health.getObject(1) + ChatColor.BOLD + ((double) health.getObject(1) * 100.0) / 100.0 + "❤" + ChatColor.GRAY + "].");

        Role killerRole = RoleUtils.getPlayerRole(killer);

        ScoreboardManager.updateScoreboard(killer);

        if (killerRole == null)
            return;

        killerRole.onPlayerKill(killer, player);

        int AOG = 0;

        for (ItemStack itemStack : killer.getInventory())
            if (itemStack != null && itemStack.getType() == Material.GOLDEN_APPLE)
                AOG += itemStack.getAmount();

        int rewardAmount = 14 - AOG;

        if (rewardAmount > 0)
            killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, rewardAmount));

        RoleUtils.armorSetup(killer.getInventory());
        killer.updateInventory();

        killer.setHealth(killer.getMaxHealth());
    }
}
