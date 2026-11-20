package be.artex.rolesffa.roles.technique.machoireTitan.items;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.roles.technique.machoireTitan.TitanMachoire;
import be.artex.rolesffa.roles.technique.machoireTitan.TransformationState;
import be.artex.rolesffa.util.StringUtils;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.api.stats.Effects;
import be.artex.strontium.itemBuilder.ItemBuilder;
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
                    ChatColor.RED + "    +3❤" + ChatColor.GRAY + " supplémentaires.", "    " +
                    StringUtils.SPEED_SIGN + ChatColor.YELLOW + " +15% de Vitesse" + ChatColor.GRAY + ".", " ",
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
        Role role = Role.getPlayerRole(player);

        if (!(role instanceof TitanMachoire)) {
            player.setItemInHand(new ItemStack(Material.AIR));
            player.updateInventory();

            return;
        }

        TitanMachoire r = (TitanMachoire) role;

        if (r.transformationState == TransformationState.TRANSFORMED) {
            player.sendMessage(Message.generate("Vous êtes déjà transformé."));
            return;
        }

        if (r.transformationTime != 0) {
            player.sendMessage(Message.cooldownTimeLeft(r.transformationTime / 20));
            return;
        }

        player.sendMessage(Message.generate("Vous vous êtes transformé."));

        r.transformationTime = 35 * 20L;
        r.transformationState = TransformationState.TRANSFORMED;

        Effects effects = Effects.get(player.getUniqueId());
        effects.addBonus(RFEffectsType.SPEED, 15);

        player.setMaxHealth(26);
        player.setHealth(player.getHealth() + 6);
    }
}
