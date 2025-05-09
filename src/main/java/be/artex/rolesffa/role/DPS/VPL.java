package be.artex.rolesffa.role.DPS;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.Stacks;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class VPL extends Role {
    @Override
    public ItemStack getItemStack() {
        return Stacks.VPL;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }

    @Override
    public Float getStrength() {
        return 11.5f;
    }
}
