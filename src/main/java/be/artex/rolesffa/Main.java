package be.artex.rolesffa;

import be.artex.rolesffa.api.items.Choose;
import be.artex.rolesffa.api.items.gyomei.Kusarigama;
import be.artex.rolesffa.api.items.sabito.Dash;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.api.items.tomura.Mains;
import be.artex.rolesffa.api.items.tomura.Unusable;
import be.artex.rolesffa.api.roles.ASV.Tomura;
import be.artex.rolesffa.api.roles.hunter.Killua;
import be.artex.rolesffa.api.roles.pirate.Mihawk;
import be.artex.rolesffa.api.roles.slayer.Gyomei;
import be.artex.rolesffa.api.roles.slayer.Sabito;
import be.artex.rolesffa.api.roles.slayer.Sanemi;
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

        SPItemUtils.registerItem(new Choose());
        SPItemUtils.registerItem(new Lame());
        SPItemUtils.registerItem(new Kusarigama());
        SPItemUtils.registerItem(new Dash());
        SPItemUtils.registerItem(new Mains());
        SPItemUtils.registerItem(new Unusable());

        RoleUtils.registerRole(new Sanemi());
        RoleUtils.registerRole(new Gyomei());
        RoleUtils.registerRole(new Sabito());
        RoleUtils.registerRole(new Mihawk());
        RoleUtils.registerRole(new Tomura());
        RoleUtils.registerRole(new Killua());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
