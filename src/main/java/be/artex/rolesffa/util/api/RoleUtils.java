package be.artex.rolesffa.util.api;

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
import java.util.Random;

public class RoleUtils {
    public static ArrayList<Role> registeredRoles = new ArrayList<>();

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

    public static void setupInventory(PlayerInventory playerInventory) {
        if (new Random().nextBoolean() && new Random().nextBoolean()) {
            playerInventory.addItem(ItemBuilder.create(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 3).build());
            playerInventory.setHelmet(ItemBuilder.create(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
            playerInventory.setBoots(ItemBuilder.create(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        } else {
            playerInventory.addItem(ItemBuilder.create(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 3).build());

            if (new Random().nextBoolean()) {
                playerInventory.setHelmet(ItemBuilder.create(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
                playerInventory.setBoots(ItemBuilder.create(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
            }

            playerInventory.setHelmet(ItemBuilder.create(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
            playerInventory.setBoots(ItemBuilder.create(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        }

        playerInventory.setChestplate(ItemBuilder.create(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setLeggings(ItemBuilder.create(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
        playerInventory.addItem(ItemBuilder.create(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).build());
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        playerInventory.addItem(new ItemStack(Material.GOLDEN_APPLE, (new Random().nextInt(7) + 12)));
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
