package be.artex.rolesffa.util.api;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Role;
import be.raft.crafty.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class RoleUtils {
    public static ArrayList<Role> registeredRoles = new ArrayList<>();
    public static HashMap<UUID, Role> playerRoles = new HashMap<>();

    public static void registerRole(Role role) {
        registeredRoles.add(role);
    }

    public static void baseSetup(Player player, Role role) {
        player.getInventory().clear();

        setupInventory(player.getInventory());

        player.setGameMode(GameMode.SURVIVAL);

        Random random = new Random();
        Location location = new Location(Bukkit.getWorlds().get(0), random.nextInt(100), 0, random.nextInt(100));

        location.setY(location.getWorld().getHighestBlockYAt(location));

        player.teleport(location);

        player.spigot().sendMessage(role.getDescription());
    }

    public static Role getPlayerRole(UUID uuid) {
        if (playerRoles.get(uuid) != null)
            return playerRoles.get(uuid);

        Main.instance.getLogger().warning(uuid.toString() + " (" + Bukkit.getPlayer(uuid).getName() + ")" + " has no role.");

        return null;
    }

    public static void setPlayerRole(UUID uuid, Role role) {
        playerRoles.put(uuid, role);
    }

    public static void setupInventory(PlayerInventory playerInventory) {
        playerInventory.setHelmet(ItemBuilder.create(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setBoots(ItemBuilder.create(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.addItem(ItemBuilder.create(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 3).build());

        playerInventory.setChestplate(ItemBuilder.create(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setLeggings(ItemBuilder.create(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        playerInventory.addItem(ItemBuilder.create(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).build());
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        playerInventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 14));
        playerInventory.addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(ItemBuilder.create(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 3).build());
        playerInventory.addItem(new ItemStack(Material.WATER_BUCKET));
        playerInventory.addItem(new ItemStack(Material.WATER_BUCKET));
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemStack(Material.ARROW, 32));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
    }
}
