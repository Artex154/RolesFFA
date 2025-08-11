package be.artex.rolesffa.roles.technique.nagisa;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Cooldown;
import be.artex.rolesffa.api.builder.item.ItemBuilder;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.rolesffa.stats.Resistance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CoupParalysant extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.NETHER_STAR)
            .name(ChatColor.AQUA + "" + ChatColor.BOLD + "Coup Paralysant")
            .lore(" ",
                    ChatColor.GRAY + " En faisant un clique droit, vous " + ChatColor.AQUA + "paralyserez " + ChatColor.GRAY + "tous les " + ChatColor.AQUA + "joueurs      ", ChatColor.GRAY + " dans un" + ChatColor.AQUA + " rayon" + ChatColor.GRAY + " de" + ChatColor.AQUA + " 10 blocs" + ChatColor.GRAY + ", pendant " + ChatColor.YELLOW + "5 secondes" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " Les " + ChatColor.YELLOW + "5 secondes" + ChatColor.GRAY + " qui suivent la " + ChatColor.AQUA + "paralysie" + ChatColor.GRAY + ", tout les joueurs " + ChatColor.AQUA + "paralysés      ", ChatColor.GRAY + " seront octroyés " + StringHelper.RESISTANCE_SIGN + ChatColor.GRAY + " -30% de resistance.      ",
                    " ", ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "40 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    public static final ArrayList<Player> FROZEN_PLAYERS = new ArrayList<>();
    public static final ArrayList<Player> RESISTANCE_DEBUFF = new ArrayList<>();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        Cooldown cooldown = Cooldown.getCooldown("coup_paralysant", 40*20L);
        Player player = event.getPlayer();

        if (cooldown.isPlayerInCooldown(player)) {
            player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "RolesFFA" + ChatColor.DARK_AQUA+ "]" + ChatColor.GRAY + " Vous êtes en cooldown pour encore " + ChatColor.AQUA + cooldown.getPlayerCooldownTimeLeft(player) + " secondes" + ChatColor.GRAY + ".");
            return;
        }

        cooldown.putPlayerInCooldown(player);

        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (!(entity instanceof Player))
                return;

            Player p = (Player) entity;

            freezePlayer(p);
        }
    }

    public static void freezePlayer(Player player) {
        FROZEN_PLAYERS.add(player);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> FROZEN_PLAYERS.remove(player), 100);

        RESISTANCE_DEBUFF.add(player);

        Resistance.setPlayerResistance(player, Resistance.getPlayerResistance(player) - 30);

        Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
            if (!RESISTANCE_DEBUFF.contains(player))
                return;

            Resistance.setPlayerResistance(player, Resistance.getPlayerResistance(player) + 30);
        }, 10*20L);
    }
}
