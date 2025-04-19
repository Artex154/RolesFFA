package be.artex.rolesffa.role.DPS.obanai;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Obanai extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Obanai";
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
    public RoleType getType() {
        return RoleType.DPS;
    }

    @Override
    public int getPlacement() {
        return 12;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
        player.getInventory().addItem(Stacks.SOUFFLE);
    }
}
