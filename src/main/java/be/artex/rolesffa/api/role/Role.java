package be.artex.rolesffa.api.role;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.listener.player.playerDamagePlayer.Strength;
import be.artex.rolesffa.scoreboard.ScoreboardManagement;
import be.raft.crafty.item.ItemBuilder;
import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public abstract class Role {
    public static ArrayList<Role> registeredRoles = new ArrayList<>();
    public static HashMap<UUID, Role> playerRoles = new HashMap<>();

    public abstract @NotNull TextComponent getDescription();
    public abstract @NotNull ItemStack getItemStack();
    public abstract void onAssigned(Player player);

    public @NotNull String getName() {
        return getItemStack().getItemMeta().getDisplayName();
    }

    public List<PotionEffect> getEffects() {
        return Collections.emptyList();
    }

    public List<ItemHolder> getItems() {
        return Collections.emptyList();
    }

    public Float getStrength() {
        return 10f;
    }

    public void onPlayerKill(PlayerDeathEvent event) {
    }

    public void onPlayerHit(EntityDamageByEntityEvent event) {
    }

    public static Role getPlayerRole(UUID uuid) {
        if (playerRoles.get(uuid) != null)
            return playerRoles.get(uuid);

        Main.instance.getLogger().warning(uuid.toString() + " (" + Bukkit.getPlayer(uuid).getName() + ")" + " has no role.");

        return null;
    }

    public static void setPlayerRole(Player player, Role role) {
        playerRoles.put(player.getUniqueId(), role);

        for (PotionEffect effect : role.getEffects()) {
            player.addPotionEffect(effect);
        }

        for (ItemHolder holder : role.getItems()) {
            ItemStack stack = holder.getItem().getItemStack();
            PlayerInventory inv = player.getInventory();

            switch (holder.getArmor()) {
                case FEET:
                    inv.setBoots(stack);
                case LEGS:
                    inv.setLeggings(stack);
                case CHEST:
                    inv.setChestplate(stack);
                case HEAD:
                    inv.setHelmet(stack);
                case HAND:
                    if (holder.getSlot() == 0)
                        inv.addItem(stack);
                    else
                        inv.setItem(holder.getSlot() - 1, stack);
            }

        }

        Strength.playerStrength.put(player.getUniqueId(), role.getStrength());

        if (role.getStrength() != 10f)
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }

    public static void registerRole(Role role, RoleType type) {
        registeredRoles.add(role);
        type.getRoles().add(role);
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

        ScoreboardManagement.openScoreboard(player);
    }

    public static void setupInventory(PlayerInventory playerInventory) {
        playerInventory.setHelmet(new ItemBuilder<>(new ItemStack(Material.DIAMOND_HELMET)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setBoots(new ItemBuilder<>(new ItemStack(Material.DIAMOND_BOOTS)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setChestplate(new ItemBuilder<>(new ItemStack(Material.DIAMOND_CHESTPLATE)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        playerInventory.setLeggings(new ItemBuilder<>(new ItemStack(Material.IRON_LEGGINGS)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build());

        playerInventory.addItem(new ItemBuilder<>(new ItemStack(Material.DIAMOND_SWORD)).addEnchant(Enchantment.DAMAGE_ALL, 3).build());
        playerInventory.addItem(new ItemBuilder<>(new ItemStack(Material.DIAMOND_PICKAXE)).addEnchant(Enchantment.DIG_SPEED, 3).build());
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        playerInventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 14));
        playerInventory.addItem(new ItemStack(Material.GOLDEN_CARROT, 64));
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemBuilder<>(new ItemStack(Material.BOW)).addEnchant(Enchantment.ARROW_DAMAGE, 3).build());
        playerInventory.addItem(new ItemStack(Material.WATER_BUCKET));
        playerInventory.addItem(new ItemStack(Material.WATER_BUCKET));
        playerInventory.addItem(new ItemStack(Material.LAVA_BUCKET));
        playerInventory.addItem(new ItemStack(Material.ARROW, 32));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
        playerInventory.addItem(new ItemStack(Material.COBBLESTONE, 64));
    }
}
