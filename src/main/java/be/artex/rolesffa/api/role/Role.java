package be.artex.rolesffa.api.role;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
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
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Role {
    public static List<Role> registeredRoles = new ArrayList<>();
    public static Map<UUID, Role> playerRoles = new HashMap<>();

    public abstract @NotNull ItemStack getItemStack();
    public abstract void onAssigned(Player player);

    public @NotNull TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .build();
    }

    public List<PotionEffect> getEffects() {
        return Collections.emptyList();
    }

    public List<ItemHolder> getItems() {
        return Collections.emptyList();
    }

    public @NotNull String getName() {
        return getItemStack().getItemMeta().getDisplayName();
    }

    public Float getStrength() {
        return 10f;
    }

    public void onPlayerKill(PlayerDeathEvent event) {
    }

    public void onPlayerHit(EntityDamageByEntityEvent event) {
    }

    public static Role getPlayerRole(UUID uuid) {
        Role role = playerRoles.get(uuid);

        if (role != null)
            return role;

        Main.instance.getLogger().warning(uuid.toString() + " (" + Bukkit.getPlayer(uuid).getName() + ")" + " has no role.");

        return null;
    }

    public static void setPlayerRole(Player player, Role role) {
        float roleStrength = role.getStrength();

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
                    break;
                case LEGS:
                    inv.setLeggings(stack);
                    break;
                case CHEST:
                    inv.setChestplate(stack);
                    break;
                case HEAD:
                    inv.setHelmet(stack);
                    break;
                case HAND:
                    if (holder.getSlot() == 0)
                        inv.addItem(stack);
                    else
                        inv.setItem(holder.getSlot() - 1, stack);
                    break;
            }

        }

        Strength.playerStrength.put(player.getUniqueId(), roleStrength);

        if (roleStrength != 10f)
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }

    public static void registerRole(Role role, RoleType type) {
        registeredRoles.add(role);
        type.addRole(role);
    }

    public static void baseSetup(Player player, Role role) {
        player.getInventory().clear();

        setupInventory(player.getInventory());

        player.setGameMode(GameMode.SURVIVAL);

        Random random = ThreadLocalRandom.current();
        Location location = new Location(Bukkit.getWorlds().get(0), random.nextInt(100), 0, random.nextInt(100));

        location.setY(location.getWorld().getHighestBlockYAt(location));

        player.teleport(location);

        player.spigot().sendMessage(role.getDescription());

        ScoreboardManagement.openScoreboard(player);
    }

    public static void setupInventory(PlayerInventory inv) {
        setArmorPiece(inv, EquipmentSlot.HEAD, Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        setArmorPiece(inv, EquipmentSlot.CHEST, Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        setArmorPiece(inv, EquipmentSlot.LEGS, Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        setArmorPiece(inv, EquipmentSlot.FEET, Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

        addItem(inv, Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 3);
        addItem(inv, Material.DIAMOND_PICKAXE, Enchantment.DIG_SPEED, 3);
        addItem(inv, Material.LAVA_BUCKET);
        addItem(inv, Material.COBBLESTONE, 64);
        addItem(inv, Material.GOLDEN_APPLE, 16);
        addItem(inv, Material.GOLDEN_CARROT, 64);
        addItem(inv, Material.LAVA_BUCKET);
        addItem(inv, Material.BOW, Enchantment.ARROW_DAMAGE, 3);
        addItem(inv, Material.WATER_BUCKET);
        addItem(inv, Material.LAVA_BUCKET);
        addItem(inv, Material.ARROW, 32);
        addItem(inv, Material.COBBLESTONE, 64);
        addItem(inv, Material.COBBLESTONE, 64);
    }

    private static void addItem(PlayerInventory inv, Material material) {
        inv.addItem(new ItemStack(material));
    }

    private static void addItem(PlayerInventory inv, Material material, int amount) {
        inv.addItem(new ItemStack(material, amount));
    }

    private static void addItem(PlayerInventory inv, Material material, Enchantment enchantment, int level) {
        inv.addItem(new ItemBuilder<>(new ItemStack(material))
                .addEnchant(enchantment, level)
                .build());
    }

    private static void setArmorPiece(PlayerInventory inv, EquipmentSlot slot, Material material, Enchantment enchantment, int level) {
        ItemStack stack = new ItemBuilder<>(new ItemStack(material))
                .addEnchant(enchantment, level)
                .build();

        switch (slot) {
            case FEET:
                inv.setBoots(stack);
                break;
            case LEGS:
                inv.setLeggings(stack);
                break;
            case CHEST:
                inv.setChestplate(stack);
                break;
            case HEAD:
                inv.setHelmet(stack);
                break;
        }
    }


}
