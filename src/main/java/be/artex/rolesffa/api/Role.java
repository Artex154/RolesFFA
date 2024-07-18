package be.artex.rolesffa.api;

import be.raft.crafty.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Random;

public interface Role {
    String getName();
    String getDescription();
    ItemStack getItemStack();
    Team getCamp();
    Team getType();
    int getPlacement();

    void onAssigned(Player player);

    class Utils {
        public static ArrayList<Role> registeredRoles = new ArrayList<>();

        public static void baseSetup(Player player) {
            player.getInventory().clear();

            setupInventory(player.getInventory());
        }

        public static void setupInventory(PlayerInventory playerInventory) {
            if (new Random().nextBoolean()) {
                playerInventory.setHelmet(ItemBuilder.create(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
                playerInventory.setBoots(ItemBuilder.create(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
            } else {
                playerInventory.setHelmet(ItemBuilder.create(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());
                playerInventory.setBoots(ItemBuilder.create(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
            }

            playerInventory.addItem(ItemBuilder.create(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 3).build());

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


        public static Location getRandomLocation() {
            return new Location(Bukkit.getWorlds().get(0), new Random().nextInt(), new Random().nextInt(), new Random().nextInt());
        }
    }
}
