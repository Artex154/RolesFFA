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

        for (int i = 0; i < 256; i++) {
            int x = i % 16;
            int z = i / 16;

            int blockX = chunkX + x;
            int blockZ = chunkZ + z;

            if (!isInRadius(blockX, blockZ, RADIUS))
                return;

            Biome biome = world.getBiome(blockX, blockZ);

            if (isOceanBiome(biome)) {
                world.setBiome(blockX, blockZ, Biome.ROOFED_FOREST);
            }
        }
    }

    private boolean isInRadius(int x, int z, int radius) {
        return (x * x + z * z) <= (radius * radius);
    }

    private boolean isOceanBiome(Biome biome) {
        return biome == Biome.OCEAN
                || biome == Biome.DEEP_OCEAN
                || biome == Biome.FROZEN_OCEAN
                || biome == Biome.SWAMPLAND;
    }
}
