package be.artex.rolesffa.role.DPS.muichiro;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.item.lame.Lame;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Muichiro extends Role {
    @Override
    public String getName() {
        return ChatColor.RED + "Muichiro";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .effect(new PotionEffect(PotionEffectType.SPEED, 0, 0))
                .item(new Lame(), new SouffleBrume())
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.MUICHIRO;
    }

    @Override
    public RoleType getType() {
        return RoleType.DPS;
    }

    @Override
    public int getPlacement() {
        return 14;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player.getUniqueId(), this);

        player.getInventory().addItem(Stacks.LAME_DE_NICHIRINE);
        player.getInventory().addItem(Stacks.SOUFFLEBRUME);

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
