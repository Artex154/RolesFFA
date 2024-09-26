package be.artex.rolesffa.api.roles.slayer;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.api.RoleUtils;
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

    // todo
    @Override
    public TextComponent getDescription() {
        return new TextComponent("sdq");
    }

    // todo
    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.IRON_SWORD);
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
        player.getInventory().setItem(0, new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD)).addEnchant(Enchantment.DAMAGE_ALL, 4).build());

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
