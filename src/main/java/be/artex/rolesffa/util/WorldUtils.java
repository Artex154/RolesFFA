package be.artex.rolesffa.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;

public class WorldUtils {
    public static boolean isWorldInitialized = false;

    public static boolean isWorldBorderInitialized(World world) {
        return world.getWorldBorder().getSize() == 200;
    }

    public static void initializeWorld() {
        int halfSize = 6;
        int centerX = 0;
        int centerY = 120;
        int centerZ = 0;
        World world = Bukkit.getWorlds().get(0);

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

        isWorldInitialized = true;
    }

    public static void initializeWorldBorder(World world) {
        WorldBorder border = world.getWorldBorder();

        border.setCenter(0, 0);
        border.setSize(200);
    }
}
