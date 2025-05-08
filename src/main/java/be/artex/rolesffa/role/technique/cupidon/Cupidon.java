package be.artex.rolesffa.role.technique.cupidon;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.item.T4;
import be.raft.crafty.item.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class Cupidon extends Role {
    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .item(new ItemHolder(new T4()))
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.CUPIDON;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);

        player.getInventory().setItem(0, new ItemBuilder<>(new ItemStack(Material.DIAMOND_SWORD)).addEnchant(Enchantment.DAMAGE_ALL, 4).build());
    }

    @Override
    public List<ItemHolder> getItems() {
        return Collections.singletonList(new ItemHolder(new Philitrum(), 8));
    }
}
