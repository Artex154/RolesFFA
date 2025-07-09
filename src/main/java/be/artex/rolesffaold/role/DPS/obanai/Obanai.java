package be.artex.rolesffaold.role.DPS.obanai;

import be.artex.rolesffaold.api.ItemHolder;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.item.lame.Lame;
import be.artex.rolesffaold.Stacks;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Obanai extends Role {
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
        return Arrays.asList(ItemHolder.inHand(new Lame()), ItemHolder.inHand(new Souffle()));
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }
}
