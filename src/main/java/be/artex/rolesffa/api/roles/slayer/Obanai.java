package be.artex.rolesffa.api.roles.slayer;

import be.artex.rolesffa.api.Role;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.items.obanai.Souffle;
import be.artex.rolesffa.api.items.sabito.Dash;
import be.artex.rolesffa.api.items.slayer.Lame;
import be.artex.rolesffa.util.Stacks;
import be.artex.rolesffa.util.api.RoleUtils;
import be.artex.rolesffa.util.builder.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Obanai extends Role {
    @Override
    public String getName() {
        return ChatColor.GREEN + "Obanai";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .effect(new PotionEffect(PotionEffectType.SPEED, 1, 0))
                .item(new Souffle(), new Lame())
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.OBANAI;
    }

    @Override
    public Team getCamp() {
        return Team.SLAYER;
    }

    @Override
    public int getPlacement() {
        return 19;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
        player.getInventory().addItem(Stacks.SOUFFLE);
    }
}
