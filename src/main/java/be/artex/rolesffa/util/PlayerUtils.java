package be.artex.rolesffa.util;

import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.stats.Effects;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerUtils {
    public static void playerSetup(Player player, Role role) {
        PlayerInventory inv = player.getInventory();

        inv.clear();
        Cooldown.clearAllCooldowns(player);
        clearFromLists(player);

        inventorySetup(inv);

        role.getItems().forEach((item) -> inv.addItem(item.getItem()));

        player.teleport(WorldUtils.computeRandomLocation(Bukkit.getWorlds().get(0)));

        player.setGameMode(GameMode.SURVIVAL);

        player.setMaxHealth(role.getMaxHealth());
        player.setHealth(role.getMaxHealth());

        Effects effects = Effects.get(player.getUniqueId());

        effects.setBonus(RFEffectsType.SPEED, role.getBonusSpeed());
        effects.setBonus(RFEffectsType.RESISTANCE, role.getBonusResistance());
        effects.setBonus(RFEffectsType.STRENGTH, role.getBonusStrength());

        Role.setPlayerRole(player, role);

        ScoreboardManager.updateScoreboard(player);

        player.sendMessage(StringUtils.LINE);
        player.spigot().sendMessage(role.getDescription());
        player.sendMessage(StringUtils.LINE);
    }

    public static void clearFromLists(Player player) {

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
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
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
