package be.artex.rolesffa.api.roles.slayer;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.items.Kusarigama;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.api.RoleUtils;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Gyomei extends Role {
    @Override
    public String getName() {
        return ChatColor.GREEN + "Gyomei";
    }

    @Override
    public TextComponent getDescription() {
        TextComponent text = new TextComponent(StringUtils.line);
        text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Rôle: " + getName() + ChatColor.GRAY + ".\n");
        text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Vous possédez Resistance I.\n");
        text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Vous possédez ");
        text.addExtra(new Kusarigama().getDescription());
        text.addExtra(ChatColor.GRAY + ".");
        text.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + "Vous possédez ");
        text.addExtra(new Lame().getDescription());
        text.addExtra(ChatColor.GRAY + ".");
        text.addExtra(StringUtils.line);

        return text;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.GYOMEI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public int getPlacement() {
        return 21;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
        player.getInventory().addItem(Stacks.KUSARIGAMA);
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
    }
}
