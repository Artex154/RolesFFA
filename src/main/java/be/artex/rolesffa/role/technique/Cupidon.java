package be.artex.rolesffa.role.technique;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.api.role.Role;
import be.raft.crafty.item.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cupidon extends Role {
    @Override
    public String getName() {
        return ChatColor.YELLOW + "Cupidon Rancunier";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .custom(ChatColor.GRAY + "Vous possédez votre" + ChatColor.GREEN + " Philtrum" + ChatColor.GRAY + " et une " + ChatColor.AQUA + "épée tranchant 4" + ChatColor.GRAY + ".")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.CUPIDON;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public int getPlacement() {
        return 11;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().setItem(0, new ItemBuilder<>(new ItemStack(Material.DIAMOND_SWORD)).addEnchant(Enchantment.DAMAGE_ALL, 4).build());
        player.getInventory().setItem(7, Stacks.PHILTRUM);
    }
}
