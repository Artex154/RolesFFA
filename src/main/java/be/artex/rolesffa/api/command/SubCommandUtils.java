package be.artex.rolesffa.api.command;

import java.util.ArrayList;
import java.util.List;

public class SubCommandUtils {
    private static final List<SubCommand> subCommands = new ArrayList<>();

    public static void addSubCommand(SubCommand command) {
        subCommands.add(command);
    }

    public static SubCommand getSubCommandFromName(String name) {
        for (SubCommand sub : subCommands)
            if (sub.getName().equalsIgnoreCase(name))
                return sub;

        return null;
    }
}
