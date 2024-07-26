package be.artex.rolesffa;

import be.artex.rolesffa.api.items.Choose;
import be.artex.rolesffa.api.items.ImmenseSpeed;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.api.roles.Sanemi;
import be.artex.rolesffa.listeners.inventory.InventoryClick;
import be.artex.rolesffa.listeners.player.*;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.api.SPItemUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        SPItemUtils.registerItem(new ImmenseSpeed());
        SPItemUtils.registerItem(new Choose());
        SPItemUtils.registerItem(new Lame());

        RoleUtils.registerRole(new Sanemi());

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEntity(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
