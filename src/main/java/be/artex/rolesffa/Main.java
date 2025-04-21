package be.artex.rolesffa;

import be.artex.rolesffa.api.item.SPItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.item.Choose;
import be.artex.rolesffa.role.DPS.*;
import be.artex.rolesffa.role.DPS.muichiro.Muichiro;
import be.artex.rolesffa.role.DPS.muichiro.SouffleBrume;
import be.artex.rolesffa.role.technique.kyojuro.Kyojuro;
import be.artex.rolesffa.role.technique.kyojuro.Purgatoire;
import be.artex.rolesffa.role.technique.shoto.Shoto;
import be.artex.rolesffa.role.technique.shoto.Fire;
import be.artex.rolesffa.role.technique.shoto.Ice;
import be.artex.rolesffa.role.tank.gyomei.Kusarigama;
import be.artex.rolesffa.role.tank.ejiro.Unbreakable;
import be.artex.rolesffa.role.DPS.obanai.Souffle;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.role.technique.tomura.mains.Mains;
import be.artex.rolesffa.role.technique.tomura.mains.Unusable;
import be.artex.rolesffa.role.technique.tomura.Tomura;
import be.artex.rolesffa.role.tank.ejiro.Ejiro;
import be.artex.rolesffa.role.tank.gyomei.Gyomei;
import be.artex.rolesffa.role.DPS.obanai.Obanai;
import be.artex.rolesffa.role.technique.cupidon.Cupidon;
import be.artex.rolesffa.listener.inventory.InventoryClick;
import be.artex.rolesffa.listener.player.*;
import be.artex.rolesffa.listener.player.EntityDamage;
import be.artex.rolesffa.listener.player.PlayerItemDamage;
import be.artex.rolesffa.listener.player.playerDamagePlayer.EntityHitEntity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Plugin instance;

    public static final String dot = ChatColor.DARK_GRAY + " • ";
    public static final String line = ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "------------------------------------";

    @Override
    public void onEnable() {
        instance = this;

        SPItem.registerItem(new Choose());
        SPItem.registerItem(new Lame());
        SPItem.registerItem(new Kusarigama());
        SPItem.registerItem(new Mains());
        SPItem.registerItem(new Unusable());
        SPItem.registerItem(new Souffle());
        SPItem.registerItem(new Unbreakable());
        SPItem.registerItem(new Ice());
        SPItem.registerItem(new Fire());
        SPItem.registerItem(new SouffleBrume());
        SPItem.registerItem(new Purgatoire());

        Role.registerRole(new Sanemi());
        Role.registerRole(new Gyomei());
        Role.registerRole(new Tomura());
        Role.registerRole(new Killua());
        Role.registerRole(new Obanai());
        Role.registerRole(new VPL());
        Role.registerRole(new Cupidon());
        Role.registerRole(new Ejiro());
        Role.registerRole(new Shoto());
        Role.registerRole(new Muichiro());
        Role.registerRole(new Knuckles());
        Role.registerRole(new Kyojuro());
        Role.registerRole(new Kokushibo());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new PlayerEat(), this);

        initializeWorld(Bukkit.getWorlds().get(0));
    }
    public static void initializeWorld(World world) {
        WorldBorder border = world.getWorldBorder();

        int halfSize = 6;
        int centerX = 0;
        int centerY = 120;
        int centerZ = 0;

        for (int x = centerX - halfSize; x <= centerX + halfSize; x++) {
            for (int z = centerZ - halfSize; z <= centerZ + halfSize; z++) {
                Block block = world.getBlockAt(x, centerY, z);
                block.setType(Material.GLASS);
            }

        }

        for (int x = centerX - halfSize - 1; x <= centerX + halfSize + 1; x++) {
            for (int z = centerZ - halfSize - 1; z <= centerZ + halfSize + 1; z++) {
                if (x == centerX - halfSize - 1 || x == centerX + halfSize + 1 || z == centerZ - halfSize - 1 || z == centerZ + halfSize + 1) {
                    for (int y = centerY; y <= centerY + 3; y++) {
                        Block block = world.getBlockAt(x, y, z);
                        block.setType(Material.GLASS);
                    }

                }

            }

        }

        border.setCenter(0, 0);
        border.setSize(200);

    }

}
