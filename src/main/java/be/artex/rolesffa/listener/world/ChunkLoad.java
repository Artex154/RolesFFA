package be.artex.rolesffa.listener.world;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoad implements Listener {
    private static final int RADIUS = 200;

    @EventHandler
    public void chunkLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        World world = chunk.getWorld();

        int chunkX = chunk.getX() << 4;
        int chunkZ = chunk.getZ() << 4;

        if (!isInRadius(chunkX, chunkZ, RADIUS + 16)) {
            return;
        }

        for (int i = 0; i < 512; i++) {
            int x = i % 16;
            int z = i / 16;

            int blockX = chunkX + x;
            int blockZ = chunkZ + z;

            world.setBiome(blockX, blockZ, Biome.ROOFED_FOREST);
        }
    }

    private boolean isInRadius(int x, int z, int radius) {
        return (x * x + z * z) <= (radius * radius);
    }
}
