package be.artex.rolesffa;

import be.artex.rolesffa.listeners.player.PlayerJoin;
import be.artex.rolesffa.listeners.player.PlayerQuit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
