package be.artex.rolesffa.helper;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class WorldHelper {
    public static void initializeWorld(World world) {
        final int centerX = 0;
        final int centerY = 120;
        final int centerZ = 0;
        final int halfSize = 6;
        final int wallHeight = 4;

        for (int x = -halfSize; x <= halfSize; x++) {
            for (int z = -halfSize; z <= halfSize; z++) {
                world.getBlockAt(centerX + x, centerY, centerZ + z).setType(Material.GLASS);
            }
        }

        int outerLimit = halfSize + 1;
        for (int x = -outerLimit; x <= outerLimit; x++) {
            for (int z = -outerLimit; z <= outerLimit; z++) {
                boolean isWallEdge = x == -outerLimit || x == outerLimit || z == -outerLimit || z == outerLimit;
                if (isWallEdge) {
                    for (int y = 0; y < wallHeight; y++) {
                        world.getBlockAt(centerX + x, centerY + y, centerZ + z).setType(Material.GLASS);
                    }
                }
            }
        }

        WorldBorder border = world.getWorldBorder();
        border.setCenter(centerX, centerZ);
        border.setSize(200);
    }
}
