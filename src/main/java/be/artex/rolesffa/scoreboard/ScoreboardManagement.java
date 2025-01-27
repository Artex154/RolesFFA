package be.artex.rolesffa.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManagement {
    public static ScoreboardManager manager = Bukkit.getScoreboardManager();

    public static void openScoreboard(Player player) {
       Scoreboard scoreboard = manager.getNewScoreboard();

       Objective objective = scoreboard.registerNewObjective("roles", "dummy");
       objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "    [RolesFFA]     -");
       objective.setDisplaySlot(DisplaySlot.SIDEBAR);

       Score nul = objective.getScore("  ");
       nul.setScore(16);

       Score joueur = objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "JOUEUR:");
       joueur.setScore(15);

       Score s2 = objective.getScore(" Rôle: " + ChatColor.AQUA + "Aucun");
       s2.setScore(14);

       Score nul2 = objective.getScore("   ");
       nul2.setScore(13);

       Score serveur = objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "SERVEUR:");
       serveur.setScore(12);

       Score joueurs = objective.getScore(" Joueurs: " + ChatColor.AQUA + 2);
       joueurs.setScore(11);

       Score nul3 = objective.getScore(" ");
       nul3.setScore(10);

       Score effets = objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "EFFETS:");
       effets.setScore(9);

       Score force = objective.getScore(" Force: " + ChatColor.AQUA + "20%");
       force.setScore(8);

       Score resistance = objective.getScore(" Resistance: " + ChatColor.AQUA + "20%");
       resistance.setScore(7);

       Score vitesse = objective.getScore(" Vitesse: " + ChatColor.AQUA + "20%");
       vitesse.setScore(6);

       player.setScoreboard(scoreboard);
    }
}
