package be.artex.rolesffa;

import be.artex.rolesffa.helper.WorldHelper;
import be.artex.rolesffa.listener.entity.player.PlayerJoin;
import be.artex.rolesffa.listener.world.ChunkLoad;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin instance;
    public static World world;

    @Override
    public void onEnable() {
        instance = this;
        world = Bukkit.getWorlds().getFirst();

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);

        WorldHelper.initializeWorld(world);

        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");

    }
}
