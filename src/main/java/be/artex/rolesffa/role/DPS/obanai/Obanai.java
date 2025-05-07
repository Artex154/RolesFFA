package be.artex.rolesffa.role.DPS.obanai;

import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Obanai extends Role {
    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.OBANAI;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<ItemHolder> getItems() {
        return Arrays.asList(new ItemHolder(new Lame()), new ItemHolder(new Souffle()));
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
