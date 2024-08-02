package be.artex.rolesffa.api.roles;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.api.RoleUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LGVengeur extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Loup-Vengeur";
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("dqsdq");
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.LG_VENGEUR;
    }

    @Override
    public Team getCamp() {
        return Team.LG;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }
}
