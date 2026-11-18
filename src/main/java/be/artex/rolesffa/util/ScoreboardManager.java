package be.artex.rolesffa.util;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.listener.entity.player.PlayerDeath;
import be.artex.rolesffa.listener.entity.player.PlayerStatsHolder;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.api.stats.Effects;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ScoreboardManager {
    public static final Map<Player, FastBoard> BOARDS = new HashMap<>();
    private static final DecimalFormat DF = new DecimalFormat("#.##");

    private static String updateHeader() {
        return " \n" + ChatColor.DARK_GRAY + ChatColor.BOLD + "» " + ChatColor.AQUA + ChatColor.BOLD + "Roles FFA" + ChatColor.DARK_GRAY + ChatColor.BOLD + " «\n" + ChatColor.WHITE + "Bonne chance!\n ";
    }

    private static String updateFooter(Player player) {
        Effects effects = Effects.get(player.getUniqueId());

        return " \n " + ChatColor.RED + ChatColor.BOLD + "⚔ " + ChatColor.RED + Math.round(effects.getBonus(RFEffectsType.STRENGTH)) + "%" + ChatColor.DARK_GRAY + " ▏ " + ChatColor.GRAY + "✦ " + Math.round(effects.getBonus(RFEffectsType.RESISTANCE)) + "%" + ChatColor.DARK_GRAY + " ▏ " + ChatColor.YELLOW + ChatColor.BOLD + "⚡ " + ChatColor.YELLOW + Math.round(effects.getBonus(RFEffectsType.SPEED)) + "%" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "\n----------------------\n" + ChatColor.AQUA + ChatColor.BOLD + "            www.azuriteuhc.fr/discord            \n ";
    }

    public static void updateHeaderFooter(Player player) {
        PacketContainer packet = new PacketContainer(
                PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER
        );

        packet.getChatComponents().write(0,
                WrappedChatComponent.fromText(updateHeader()));

        packet.getChatComponents().write(1,
                WrappedChatComponent.fromText(updateFooter(player)));

        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    }

    public static void updateScoreboard(Player player) {
        FastBoard board = BOARDS.get(player);

        updateScoreboard(player, board);
    }

    public static void updateScoreboard(Player player, FastBoard board) {
        board.updateLines(
                ChatColor.GRAY + "  " + ChatColor.ITALIC + getDate(),
                " ",
                ChatColor.DARK_AQUA + "» " + ChatColor.AQUA + ChatColor.BOLD + "Informations",
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Joueurs: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size(),
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Rôle: " + ChatColor.AQUA + getRole(player),
                " ",
                ChatColor.DARK_AQUA + "» " + ChatColor.AQUA + ChatColor.BOLD + "Statistiques",
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "Kills: " + ChatColor.AQUA + getKillAmount(player),
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "KS: " + ChatColor.AQUA + getKillStreakAmount(player),
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " - " + ChatColor.WHITE + "KDA: " + ChatColor.AQUA + getKDA(player),
                " ",
                ChatColor.GRAY + " azuriteuhc.fr"
        );
    }

    private static String getRole(Player pl) {
        Role role = Role.getPlayerRole(pl);

        if (role == null)
            return "Aucun";

        return role.getName();
    }

    private static int getKillAmount(Player pl) {
        PlayerStatsHolder holder = PlayerDeath.PLAYER_STATS.get(pl.getUniqueId());

        if (holder == null)
            return 0;

        return holder.getKillAmount();
    }

    private static int getKillStreakAmount(Player pl) {
        PlayerStatsHolder holder = PlayerDeath.PLAYER_STATS.get(pl.getUniqueId());

        if (holder == null)
            return 0;

        return holder.getKillStreakAmount();
    }

    private static String getKDA(Player pl) {
        PlayerStatsHolder holder = PlayerDeath.PLAYER_STATS.get(pl.getUniqueId());

        if (holder == null)
            return "0";

        return DF.format(holder.getKDA());
    }

    private static String getDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return date.format(formatter);
    }
}
