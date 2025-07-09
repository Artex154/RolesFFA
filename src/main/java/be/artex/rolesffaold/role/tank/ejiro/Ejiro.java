package be.artex.rolesffaold.role.tank.ejiro;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.ItemHolder;
import be.artex.rolesffaold.api.role.Role;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class Ejiro extends Role {
    @Override
    public ItemStack getItemStack() {
        return Stacks.EJIRO;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<ItemHolder> getItems() {
        return Collections.singletonList(ItemHolder.inHand(new Unbreakable()));
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
    }
}
