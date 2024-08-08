package be.artex.rolesffa.api.roles.slayer;

import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.items.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.util.api.RoleUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sanemi extends Role {

    @Override
    public String getName() {
        return ChatColor.GREEN + "Sanemi";
    }

    @Override
    public TextComponent getDescription() {
        TextComponent description = new TextComponent(StringUtils.line);
        description.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + " Rôle: " + getName());
        description.addExtra("\n");
        description.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + " Vous possédez " + ChatColor.RED + "Force I " + ChatColor.GRAY + "de façon permanente.");
        description.addExtra("\n");
        description.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + " Quand vous tuez un " + ChatColor.AQUA + "joueur" + ChatColor.GRAY + ", vous gagnerez " + ChatColor.YELLOW + "7% de vitesse" + ChatColor.GRAY + " supplémentaire. (stackable)");
        description.addExtra("\n");
        description.addExtra("\n" + StringUtils.dot + ChatColor.GRAY + " Vous possédez une ");
        description.addExtra(new Lame().getDescription());
        description.addExtra(ChatColor.GRAY + ".");
        description.addExtra(StringUtils.line);

        return description;
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SANEMI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
    }

    @Override
    public void onPlayerKill(PlayerDeathEvent event) {
         event.getEntity().getKiller().setWalkSpeed((event.getEntity().getKiller().getWalkSpeed() / 100) * 107);
         event.getEntity().getKiller().sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + ChatColor.BOLD + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + " Vous avez gagné " + ChatColor.DARK_AQUA + ChatColor.BOLD + "7% de vitesse" + ChatColor.AQUA + ".");
    }
}
