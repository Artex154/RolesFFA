package be.artex.rolesffa;

import be.artex.rolesffa.api.item.items.Choose;
import be.artex.rolesffa.api.item.items.gyomei.Kusarigama;
import be.artex.rolesffa.api.item.items.obanai.Souffle;
import be.artex.rolesffa.api.item.items.sabito.Dash;
import be.artex.rolesffa.api.item.items.slayer.lame.Lame;
import be.artex.rolesffa.api.item.items.tomura.Mains;
import be.artex.rolesffa.api.item.items.tomura.Unusable;
import be.artex.rolesffa.api.role.roles.ASV.Tomura;
import be.artex.rolesffa.api.role.roles.LG.VPL;
import be.artex.rolesffa.api.role.roles.hunter.Killua;
import be.artex.rolesffa.api.role.roles.pirate.Mihawk;
import be.artex.rolesffa.api.role.roles.slayer.Gyomei;
import be.artex.rolesffa.api.role.roles.slayer.Obanai;
import be.artex.rolesffa.api.role.roles.slayer.Sabito;
import be.artex.rolesffa.api.role.roles.slayer.Sanemi;
import be.artex.rolesffa.listeners.inventory.InventoryClick;
import be.artex.rolesffa.listeners.player.*;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.api.item.SPItemUtils;
import be.artex.rolesffa.listeners.player.playerDamagePlayer.PlayerItemDamage;
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

        SPItemUtils.registerItem(new Choose());
        SPItemUtils.registerItem(new Lame());
        SPItemUtils.registerItem(new Kusarigama());
        SPItemUtils.registerItem(new Dash());
        SPItemUtils.registerItem(new Mains());
        SPItemUtils.registerItem(new Unusable());
        SPItemUtils.registerItem(new Souffle());

        RoleUtils.registerRole(new Sanemi());
        RoleUtils.registerRole(new Gyomei());
        RoleUtils.registerRole(new Sabito());
        RoleUtils.registerRole(new Mihawk());
        RoleUtils.registerRole(new Tomura());
        RoleUtils.registerRole(new Killua());
        RoleUtils.registerRole(new Obanai());
        //RoleUtils.registerRole(new LoupGarou());
        RoleUtils.registerRole(new VPL());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);

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
