package be.artex.rolesffa.commands.subCommands;

import be.artex.rolesffa.api.command.SubCommand;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.stats.StatValues;
import be.artex.rolesffa.stats.Stats;
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
        Stats stats = Stats.get(player.getUniqueId());

        TextComponent text = new TextComponent(StringHelper.LINE);
        text.addExtra("\n" + ChatColor.GRAY + " Vos pourcentages d'effets:");
        text.addExtra("\n ");
        text.addExtra(StringHelper.speedSignHover());
        text.addExtra(ChatColor.YELLOW + " Vitesse" + ChatColor.GRAY + " -> " + ChatColor.YELLOW + stats.getBonus(StatValues.SPEED) + "%");
        text.addExtra("\n ");
        text.addExtra(StringHelper.resistanceSignHover());
        text.addExtra(ChatColor.GRAY + " Resistance -> " + stats.getBonus(StatValues.RESISTANCE) + "%");
        text.addExtra("\n ");
        text.addExtra(StringHelper.strengthSignHover());
        text.addExtra(ChatColor.RED + " Force " + ChatColor.GRAY + "-> " + ChatColor.RED + stats.getBonus(StatValues.STRENGTH) + "%");
        text.addExtra("\n" + StringHelper.LINE);

        player.spigot().sendMessage(text);

    }
}
