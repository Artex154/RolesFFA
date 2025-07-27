package be.artex.rolesffa;

import be.artex.rolesffa.commands.RFCommand;
import be.artex.rolesffa.commands.subCommands.EffectsSubCommand;
import be.artex.rolesffa.helper.WorldHelper;
import be.artex.rolesffa.listener.entity.EntityDamageByEntity;
import be.artex.rolesffa.listener.entity.player.*;
import be.artex.rolesffa.listener.inventory.InventoryClick;
import be.artex.rolesffa.listener.world.ChunkLoad;
import be.artex.rolesffa.roles.dps.Kokushibo;
import be.artex.rolesffa.roles.dps.VPL;
import be.artex.rolesffa.roles.technique.nagisa.Nagisa;
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
        world = Bukkit.getWorlds().get(0);

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        getCommand("rf").setExecutor(new RFCommand());

        WorldHelper.initializeWorld(world);

        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");

        RFItems.registerItems();

        new Kokushibo().register();
        new VPL().register();
        new Nagisa().register();

        new EffectsSubCommand().register();
    }
}
