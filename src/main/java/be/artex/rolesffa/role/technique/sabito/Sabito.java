package be.artex.rolesffa.role.technique.sabito;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.raft.crafty.item.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sabito extends Role {

    @Override
    public String getName() {
        return ChatColor.YELLOW + "Sabito";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .item(new Dash(), new Lame())
                .effect(new PotionEffect(PotionEffectType.SPEED, 1, 0))
                .custom(ChatColor.GRAY + "Vous possédez des bottes " + ChatColor.BLUE + "depth strider 2" + ChatColor.GRAY + ".")
                .build();
    }
    @Override
    public ItemStack getItemStack() {
        return Stacks.SABITO;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public int getPlacement() {
        return 12;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
        player.getInventory().addItem(Stacks.SABITO_DASH);
        player.getInventory().setBoots(new ItemBuilder<>(new ItemStack(Material.DIAMOND_BOOTS)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DEPTH_STRIDER, 2).build());

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
