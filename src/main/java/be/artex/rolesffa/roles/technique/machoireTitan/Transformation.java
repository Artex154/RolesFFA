package be.artex.rolesffa.roles.technique.machoireTitan;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.stats.Speed;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Transformation extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.FEATHER)
            .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Transformation")
            .lore(" ",
                    ChatColor.GRAY + " En faisant un clique droit, vous vous " + ChatColor.AQUA + "transformerez. ", ChatColor.GRAY + " Dans cette forme, vous possédez les " + ChatColor.AQUA + "effets " + ChatColor.GRAY + "et" + ChatColor.AQUA + " items" + ChatColor.GRAY + " suivants, pendant " + ChatColor.YELLOW + "35 secondes" + ChatColor.GRAY + ".       ", " ",
                    ChatColor.LIGHT_PURPLE + "    +3 coeurs" + ChatColor.GRAY + " supplémentaires.", "    " +
                    StringHelper.SPEED_SIGN + ChatColor.AQUA + " +15% de Vitesse" + ChatColor.GRAY + ".", " ",
                    ChatColor.AQUA + "" + ChatColor.BOLD + "   Mâchoire" + ChatColor.GRAY + ": Cet item vous permettra d'infliger " + ChatColor.LIGHT_PURPLE + "2 coeurs et demi", ChatColor.GRAY + "    quand vous taperez un" + ChatColor.AQUA + " joueur " + ChatColor.GRAY + "avec. Il possède un cooldown de " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " Cependant, vous ne pourrez plus utiliser", ChatColor.GRAY + " l'" + ChatColor.GOLD + ChatColor.BOLD + "Equipement Tridimensionnelle" + ChatColor.GRAY + " durant la transformation.", " ",
                    ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "50 secondes" + ChatColor.GRAY + " après l'utilisation", " ")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (RoleUtils.transformedPlayers.contains(player)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA+ "]" + ChatColor.GRAY + " Vous êtes déjà transformé.");
            return;
        }

        Cooldown cooldown = Cooldown.getCooldown("machoire_transfo", 85*20L);

        if (cooldown.isPlayerInCooldown(player)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA + "]" + ChatColor.GRAY + " Vous êtes en cooldown pour encore " + ChatColor.AQUA + cooldown.getPlayerCooldownTimeLeft(player) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        cooldown.putPlayerInCooldown(player);

        RoleUtils.transformedPlayers.add(player);

        Speed.setPlayerSpeed(player, 115);

        player.setMaxHealth(26);
        player.setHealth(player.getHealth() + 6);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (!RoleUtils.transformedPlayers.contains(player))
                return;

            RoleUtils.transformedPlayers.remove(player);

            player.setMaxHealth(20);
            Speed.setPlayerSpeed(player, 100);
        }, 35*20L);
    }
}
