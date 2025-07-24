package be.artex.rolesffa.api.role;

import be.artex.rolesffa.api.builder.item.EnchantmentHolder;
import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.stats.Speed;
import be.artex.rolesffa.stats.Strength;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RoleUtils {
    private static final List<Role> roles = new ArrayList<>(); // TODO: Use the Registry system instead
    private static final HashMap<Player, Role> playerRole = new HashMap<>();

    public static RoleType getRoleTypeFromItem(ItemStack stack) {
        for (RoleType type : RoleType.values())
            if (type.getItem().isSimilar(stack))
                return type;

        return null;
    }

    public static Role getRoleFromItem(ItemStack stack) {
        for (Role role : roles)
            if (role.getStack().isSimilar(stack))
                return role;

        return null;
    }

    public static Role getPlayerRole(Player player) {
        return playerRole.getOrDefault(player, null);
    }

    public static void setPlayerRole(Player player, Role role) {
        playerRole.put(player, role);
    }

    public static void addRole(Role role) {
        roles.add(role);
    }

    public static void playerSetup(Player player, Role role) {
        PlayerInventory inv = player.getInventory();

        inv.clear();
        inv.setArmorContents(null);

        inventorySetup(inv);

        role.getItems().forEach((item) -> inv.addItem(item.getItem()));

        player.teleport(computeRandomLocation(Bukkit.getWorlds().getFirst()));

        player.setGameMode(GameMode.SURVIVAL);

        player.setMaxHealth(role.getMaxHealth());

        Speed.setPlayerSpeed(player, role.getSpeed());
        Strength.setPlayerStrength(player, role.getStrength());

        player.sendMessage(role.getDescription().getText());

        setPlayerRole(player, role);
    }

    private static Location computeRandomLocation(World world) {
        Random random = new Random();

        int x = random.nextInt(50);
        int z = random.nextInt(50);

        Location loc = new Location(world, x, 0, z);
        loc.setY(world.getHighestBlockYAt(loc));

        return loc;
    }

    private static void inventorySetup(PlayerInventory inventory) {
        inventory.setHelmet(new ItemBuilder(Material.IRON_HELMET).
                addEnchants(new EnchantmentHolder(Enchantment.PROTECTION_ENVIRONMENTAL, 3))
                .build());
        inventory.setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).
                addEnchants(new EnchantmentHolder(Enchantment.PROTECTION_ENVIRONMENTAL, 2))
                .build());
        inventory.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).
                addEnchants(new EnchantmentHolder(Enchantment.PROTECTION_ENVIRONMENTAL, 3))
                .build());
        inventory.setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).
                addEnchants(new EnchantmentHolder(Enchantment.PROTECTION_ENVIRONMENTAL, 2))
                .build());

        inventory.addItem(new ItemBuilder(Material.DIAMOND_SWORD).
                addEnchants(new EnchantmentHolder(Enchantment.DAMAGE_ALL, 3))
                .build());
        inventory.addItem(new ItemBuilder(Material.BOW).
                addEnchants(new EnchantmentHolder(Enchantment.ARROW_DAMAGE, 3))
                .build());
        inventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        inventory.addItem(new ItemStack(Material.LEAVES, 64));
        inventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 14));
        inventory.addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
        inventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        inventory.addItem(new ItemStack(Material.WATER_BUCKET));
        inventory.addItem(new ItemStack(Material.WATER_BUCKET));
        inventory.addItem(new ItemStack(Material.ARROW, 24));
        inventory.addItem(new ItemStack(Material.LEAVES, 64));
        inventory.addItem(new ItemStack(Material.LEAVES, 64));
    }
}
