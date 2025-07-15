package be.artex.rolesffa.commands.subCommands;

import be.artex.rolesffa.api.command.SubCommand;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.stats.Resistance;
import be.artex.rolesffa.stats.Speed;
import be.artex.rolesffa.stats.Strength;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EffectsSubCommand extends SubCommand {
    @Override
    public String getName() {
        return "effects";
    }

    @Override
    public void onExecution(Player player, String[] args) {
        TextComponent text = new TextComponent(StringHelper.LINE);
        text.addExtra("\n" + ChatColor.GRAY + " Vos pourcentages d'effets:");
        text.addExtra("\n ");
        text.addExtra(StringHelper.speedSignHover());
        text.addExtra(ChatColor.AQUA + " Vitesse" + ChatColor.GRAY + " -> " + ChatColor.AQUA + Speed.getPlayerSpeed(player) + "%");
        text.addExtra("\n ");
        text.addExtra(StringHelper.resistanceSignHover());
        text.addExtra(ChatColor.GRAY + " Resistance -> " + Resistance.getPlayerResistance(player) + "%");
        text.addExtra("\n ");
        text.addExtra(StringHelper.strengthSignHover());
        text.addExtra(ChatColor.RED + " Force " + ChatColor.GRAY + "-> " + ChatColor.RED + Strength.getPlayerStrength(player) + "%");
        text.addExtra("\n" + StringHelper.LINE);

        player.spigot().sendMessage(text);

    }
}
