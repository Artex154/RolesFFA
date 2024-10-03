package be.artex.rolesffa.api.roles.ASV;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.Strength;
import be.artex.rolesffa.util.api.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Tomura extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Tomura";
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("qsd");
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.TOMURA;
    }

    @Override
    public Team getCamp() {
        return Team.SUP_VIL;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.MAINS);
    }
}
