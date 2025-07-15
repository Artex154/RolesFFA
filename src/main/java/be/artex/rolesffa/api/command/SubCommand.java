package be.artex.rolesffa.api.command;

import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName();

    public abstract void onExecution(Player player, String[] args);

    public void register() {
        SubCommandUtils.addSubCommand(this);
    }
}
