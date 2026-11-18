package be.artex.rolesffa.commands.subCommands;

import be.artex.rolesffa.api.command.SubCommand;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.api.stats.Effects;
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
        Effects effects = Effects.get(player.getUniqueId());

        TextComponent text = new TextComponent(StringUtils.LINE);
        text.addExtra("\n" + ChatColor.GRAY + " Vos pourcentages d'effets:");
        text.addExtra("\n ");
        text.addExtra(StringUtils.speedSignHover());
        text.addExtra(ChatColor.YELLOW + " Vitesse" + ChatColor.GRAY + " -> " + ChatColor.YELLOW + effects.getBonus(RFEffectsType.SPEED) + "%");
        text.addExtra("\n ");
        text.addExtra(StringUtils.resistanceSignHover());
        text.addExtra(ChatColor.GRAY + " Resistance -> " + effects.getBonus(RFEffectsType.RESISTANCE) + "%");
        text.addExtra("\n ");
        text.addExtra(StringUtils.strengthSignHover());
        text.addExtra(ChatColor.RED + " Force " + ChatColor.GRAY + "-> " + ChatColor.RED + effects.getBonus(RFEffectsType.STRENGTH) + "%");
        text.addExtra("\n" + StringUtils.LINE);

        player.spigot().sendMessage(text);

    }
}
