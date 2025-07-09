package be.artex.rolesffa;

import be.artex.rolesffa.helper.WorldHelper;
import be.artex.rolesffa.listener.entity.player.PlayerJoin;
import be.artex.rolesffa.listener.world.ChunkLoad;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);

        WorldHelper.initializeWorld(Bukkit.getWorlds().getFirst());
    }
}
