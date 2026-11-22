package be.artex.rolesffa;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.weather.Weather;
import be.artex.rolesffa.api.weather.WeatherType;
import be.artex.rolesffa.commands.RFCommand;
import be.artex.rolesffa.commands.subCommands.EffectsSubCommand;
import be.artex.rolesffa.util.ScoreboardManager;
import be.artex.rolesffa.util.WorldUtils;
import be.artex.rolesffa.listener.entity.EntityDamage;
import be.artex.rolesffa.listener.entity.EntityDamageByEntity;
import be.artex.rolesffa.listener.entity.player.*;
import be.artex.rolesffa.listener.inventory.InventoryClick;
import be.artex.rolesffa.listener.world.ChunkLoad;
import be.artex.rolesffa.registries.RFItemRegistries;
import be.artex.rolesffa.registries.RoleRegistries;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
    public static Plugin instance;
    public static World world;

    @Override
    public void onEnable() {
        instance = this;
        world = Bukkit.getWorlds().get(0);

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerDismount(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerFish(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerStatChange(), this);

        getCommand("rf").setExecutor(new RFCommand());

        WorldUtils.initializeWorld(world);

        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");

        RFItemRegistries.registerItems();
        RoleRegistries.registerRoles();

        new EffectsSubCommand().register();

        Weather.replaceCurrentWeather(Weather.getRandomWeather());

        new BukkitRunnable() {
            int amountOfRuns = 0;

            @Override
            public void run() {
                amountOfRuns++;

                if (amountOfRuns == 301) {
                    WeatherType weatherType = Weather.getRandomWeather();

                    Weather.replaceCurrentWeather(weatherType);

                    amountOfRuns = 0;

                    Bukkit.broadcastMessage(Message.generate("Le temps change en " + weatherType.getColor() + ChatColor.BOLD + weatherType.getName().toUpperCase() + ChatColor.GRAY + "."));

                    for (Player player : Bukkit.getOnlinePlayers())
                        ScoreboardManager.updateScoreboard(player);
                }

                Role.playerRole.forEach((p, r) -> {
                    if (r.shouldTick())
                        r.tick(p);
                });
            }
        }.runTaskTimer(Main.instance, 0L, 10L);
    }
}
