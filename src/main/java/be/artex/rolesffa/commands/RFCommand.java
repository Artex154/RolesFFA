package be.artex.rolesffa.commands;

import be.artex.rolesffa.api.command.SubCommand;
import be.artex.rolesffa.api.command.SubCommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RFCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length == 0)
            return false;

        SubCommand sub = SubCommandUtils.getSubCommandFromName(args[0]);

        if (sub != null) {
            sub.onExecution((Player) sender, args);
            return true;
        }

        return false;
    }
}
