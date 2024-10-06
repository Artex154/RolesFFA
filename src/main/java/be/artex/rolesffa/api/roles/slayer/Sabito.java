package be.artex.rolesffa.api.roles.slayer;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.items.sabito.Dash;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.builder.DescriptionBuilder;
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
        return ChatColor.GREEN + "Sabito";
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
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public int getPlacement() {
        return 22;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
        player.getInventory().addItem(Stacks.SABITO_DASH);
        player.getInventory().setBoots(new ItemBuilder<>(new ItemStack(Material.DIAMOND_BOOTS)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DEPTH_STRIDER, 2).build());

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
