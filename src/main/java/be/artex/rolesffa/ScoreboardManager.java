package be.artex.rolesffa;

import be.artex.rolesffa.stats.StatValues;
import be.artex.rolesffa.stats.Stats;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ScoreboardManager {
    private static String updateHeader() {
        return " \n" + ChatColor.DARK_GRAY + ChatColor.BOLD + "» " + ChatColor.AQUA + ChatColor.BOLD + "Roles FFA" + ChatColor.DARK_GRAY + ChatColor.BOLD + " «\n" + ChatColor.WHITE + "Bonne chance!\n ";
    }

    private static String updateFooter(Player player) {
        Stats stats = Stats.get(player.getUniqueId());

        return " \n " + ChatColor.RED + ChatColor.BOLD + "⚔ " + ChatColor.RED + Math.round(stats.getBonus(StatValues.STRENGTH)) + "%" + ChatColor.DARK_GRAY + " ▏ " + ChatColor.GRAY + "✦ " + Math.round(stats.getBonus(StatValues.RESISTANCE)) + "%" + ChatColor.DARK_GRAY + " ▏ " + ChatColor.YELLOW + ChatColor.BOLD + "⚡ " + ChatColor.YELLOW + Math.round(stats.getBonus(StatValues.SPEED)) + "%" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "\n----------------------\n" + ChatColor.AQUA + ChatColor.BOLD + "            www.azuriteuhc.fr/discord            \n ";
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

    public static void updateScoreboard(Player player, FastBoard board) {
        board.updateLines(
                " ",
                ChatColor.DARK_AQUA + "   • " + ChatColor.AQUA + ChatColor.BOLD + "SERVEUR" + ChatColor.DARK_AQUA + " •",
                ChatColor.DARK_GRAY + " ▏  " + ChatColor.GRAY + "Joueurs: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size(),
                ChatColor.DARK_GRAY + " ▏  " + ChatColor.GRAY + "Kills: " + ChatColor.AQUA + "1",
                " ",
                ChatColor.DARK_AQUA + "   • " + ChatColor.AQUA + ChatColor.BOLD + "TEMPS" + ChatColor.DARK_AQUA + " •",
                ChatColor.DARK_GRAY + " ▏  " + ChatColor.GRAY + "Temps: " + ChatColor.AQUA + "2mn",
                ChatColor.DARK_GRAY + " ▏  " + ChatColor.GRAY + "Episode: " + ChatColor.AQUA + "2",
                " ",
                ChatColor.AQUA + " azuriteuhc.fr"
        );
    }
}
