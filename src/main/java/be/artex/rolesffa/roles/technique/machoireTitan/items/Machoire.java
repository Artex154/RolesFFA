package be.artex.rolesffa.roles.technique.machoireTitan.items;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.roles.technique.machoireTitan.TitanMachoire;
import be.artex.rolesffa.roles.technique.machoireTitan.TransformationState;
import be.artex.strontium.itemBuilder.ItemBuilder;
import be.artex.strontium.player.StrontPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Machoire extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.NETHER_STAR)
            .name(ChatColor.AQUA + "" + ChatColor.BOLD + "Mâchoire")
            .lore(" ",
                    ChatColor.GRAY + " En " + ChatColor.RED + "tappant " + ChatColor.GRAY + "un " + ChatColor.AQUA + "joueur " + ChatColor.GRAY + "avec, vous " + ChatColor.RED + "infligerez" + ChatColor.RED + " 2,5❤ " + ChatColor.GRAY + "de " + ChatColor.RED + "dégats" + ChatColor.GRAY + ".      ", ChatColor.GRAY + " Cependant, vous pouvez utiliser que cet item en étant " + ChatColor.AQUA + "transformé" + ChatColor.GRAY + ".       ", " ", ChatColor.GRAY + " Cooldown : " + ChatColor.YELLOW + "10 secondes" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        Role role = Role.getPlayerRole(player);

        if (!(role instanceof TitanMachoire)) {
            player.setItemInHand(new ItemStack(Material.AIR));
            player.updateInventory();

            return;
        }

        TitanMachoire r = (TitanMachoire) role;

        if (r.transformationState == TransformationState.NOT_TRANSFORMED) {
            player.sendMessage(Message.generate("Vous devez être transformé pour utilser votre mâchoire !"));
            return;
        }

        if (r.machoireCDT != 0) {
            player.sendMessage(Message.cooldownTimeLeft(r.machoireCDT / 20));
            return;
        }

        r.machoireCDT = 10 * 20L;

        StrontPlayer.getSrontPlayer(((Player) event.getEntity())).inflictTrueDamage(5);
    }
}
