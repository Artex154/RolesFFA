package be.artex.rolesffa.roles.dps.chainsaw;

import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Pacte extends RFItem {
    private static final ItemStack STACK = new ItemBuilder(Material.QUARTZ)
            .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Changement de Forme")
            .lore(" ",
                    ChatColor.GRAY + " En faisant un clique droit, vous changerez de " + ChatColor.GOLD + "forme" + ChatColor.GRAY + ".", ChatColor.GRAY + " En" + ChatColor.GOLD + " forme chainsaw" + ChatColor.GRAY + ", vous posséderez " + StringUtils.SPEED_SIGN + ChatColor.AQUA + " 120% de vitesse.       ",
                    ChatColor.GRAY + " Ainsi que " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 120% de force." + ChatColor.GRAY + " Et d'un " + ChatColor.AQUA + ChatColor.BOLD + "Dash" + ChatColor.GRAY + ".", " ",
                    ChatColor.GRAY + " Cependant, vous ne posséderez " + ChatColor.RED + "plus" + ChatColor.GRAY + " de casque.", "",
                    ChatColor.GRAY + " Vous pouvez rechangez de " + ChatColor.GOLD + "mode " + ChatColor.GRAY + "en faisant un clique droit.",
                            " ")
            .build();

    @Override
    public ItemStack getItem() {
        return STACK;
    }
}
