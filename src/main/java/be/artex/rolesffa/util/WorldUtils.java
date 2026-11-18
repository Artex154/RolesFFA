package be.artex.rolesffa.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class WorldUtils {
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

    }

    public static Location computeRandomLocation(World world) {
        Random random = new Random();

        int x = random.nextInt(25);
        int z = random.nextInt(25);

        if (Math.random() < 0.5)
            x = -x;
        if (Math.random() < 0.5)
            z = -z;

        int y = world.getHighestBlockYAt(x, z);

        return new Location(world, x, y, z);
    }

}
