package be.artex.rolesffa.api.role;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;
import be.artex.rolesffa.stats.StatValues;
import be.artex.rolesffa.stats.Stats;
import be.artex.strontium.itemBuilder.ItemBuilder;
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
    public static final List<Player> transformedPlayers = new ArrayList<>();

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
        Cooldown.clearAllCooldowns(player);
        clearFromLists(player);

        inventorySetup(inv);

        role.getItems().forEach((item) -> inv.addItem(item.getItem()));

        player.teleport(computeRandomLocation(Bukkit.getWorlds().get(0)));

        player.setGameMode(GameMode.SURVIVAL);

        player.setMaxHealth(role.getMaxHealth());
        player.setHealth(role.getMaxHealth());

        Stats stats = Stats.get(player.getUniqueId());

        stats.setBonus(StatValues.SPEED, role.getBonusSpeed());
        stats.setBonus(StatValues.RESISTANCE, role.getBonusResistance());
        stats.setBonus(StatValues.STRENGTH, role.getBonusStrength());

        player.sendMessage(role.getDescription().getText());
        setPlayerRole(player, role);
    }

    private static void clearFromLists(Player player) {
        CoupParalysant.RESISTANCE_DEBUFF.remove(player);
        CoupParalysant.FROZEN_PLAYERS.remove(player);
    }

    private static Location computeRandomLocation(World world) {
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

    private static void inventorySetup(PlayerInventory inventory) {
        armorSetup(inventory);

        inventory.addItem(new ItemBuilder(Material.DIAMOND_SWORD)
                .addEnchant(Enchantment.DAMAGE_ALL, 3)
                .build());
        inventory.addItem(new ItemBuilder(Material.BOW)
                .addEnchant(Enchantment.ARROW_DAMAGE, 3)
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

    public static void armorSetup(PlayerInventory inventory) {
        inventory.setHelmet(new ItemBuilder(Material.DIAMOND_HELMET)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .build());
        inventory.setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .build());
        inventory.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                .build());
        inventory.setBoots(new ItemBuilder(Material.DIAMOND_BOOTS)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .build());
    }
}
