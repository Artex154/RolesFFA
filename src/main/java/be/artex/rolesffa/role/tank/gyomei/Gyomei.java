package be.artex.rolesffa.role.tank.gyomei;

import be.artex.rolesffa.api.ItemHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.item.lame.Lame;
import be.artex.rolesffa.Stacks;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gyomei extends Role {
    @Override
    public ItemStack getItemStack() {
        return Stacks.GYOMEI;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
    }

    @Override
    public List<ItemHolder> getItems() {
        return Arrays.asList(ItemHolder.inHand(new Lame()), ItemHolder.inHand(new Kusarigama()));
    }
}
