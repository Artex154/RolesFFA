package be.artex.rolesffa;

import be.artex.rolesffa.item.Choose;
import be.artex.rolesffa.item.GoldenHead;
import be.artex.rolesffa.role.slayer.gyomei.Kusarigama;
import be.artex.rolesffa.role.heros.kenji.Unbreakable;
import be.artex.rolesffa.role.slayer.obanai.Souffle;
import be.artex.rolesffa.role.slayer.sabito.Dash;
import be.artex.rolesffa.role.slayer.item.lame.Lame;
import be.artex.rolesffa.role.allianceSV.tomura.mains.Mains;
import be.artex.rolesffa.role.allianceSV.tomura.mains.Unusable;
import be.artex.rolesffa.role.allianceSV.tomura.Tomura;
import be.artex.rolesffa.role.loupGarou.VPL;
import be.artex.rolesffa.role.heros.kenji.Kenji;
import be.artex.rolesffa.role.hunter.Killua;
import be.artex.rolesffa.role.pirate.Mihawk;
import be.artex.rolesffa.role.slayer.gyomei.Gyomei;
import be.artex.rolesffa.role.slayer.obanai.Obanai;
import be.artex.rolesffa.role.slayer.sabito.Sabito;
import be.artex.rolesffa.role.slayer.Sanemi;
import be.artex.rolesffa.role.village.Cupidon;
import be.artex.rolesffa.listener.inventory.InventoryClick;
import be.artex.rolesffa.listener.player.*;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.api.item.SPItemUtils;
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

        SPItemUtils.registerItem(new Choose());
        SPItemUtils.registerItem(new Lame());
        SPItemUtils.registerItem(new Kusarigama());
        SPItemUtils.registerItem(new Dash());
        SPItemUtils.registerItem(new Mains());
        SPItemUtils.registerItem(new Unusable());
        SPItemUtils.registerItem(new Souffle());
        SPItemUtils.registerItem(new Unbreakable());
        SPItemUtils.registerItem(new GoldenHead());

        RoleUtils.registerRole(new Sanemi());
        RoleUtils.registerRole(new Gyomei());
        RoleUtils.registerRole(new Sabito());
        RoleUtils.registerRole(new Mihawk());
        RoleUtils.registerRole(new Tomura());
        RoleUtils.registerRole(new Killua());
        RoleUtils.registerRole(new Obanai());
        //RoleUtils.registerRole(new LoupGarou());
        RoleUtils.registerRole(new VPL());
        RoleUtils.registerRole(new Cupidon());
        RoleUtils.registerRole(new Kenji());

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
