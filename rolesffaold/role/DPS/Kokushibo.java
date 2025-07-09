package be.artex.rolesffaold.role.DPS;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.ItemHolder;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.builder.description.DescriptionBuilder;
import be.artex.rolesffaold.item.lame.Lame;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.List;

public class Kokushibo extends Role {
    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .onKill("vous gagnerez un " + ChatColor.LIGHT_PURPLE + "coeur permanent")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KOKUSHIBO;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public void onPlayerKill(PlayerDeathEvent event) {
        event.getEntity().getKiller().setMaxHealth(event.getEntity().getKiller().getMaxHealth() + 2);
    }

    @Override
    public List<PotionEffect> getEffects() {
        return Collections.singletonList(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
    }

    @Override
    public List<ItemHolder> getItems() {
        return Collections.singletonList(ItemHolder.inHand(new Lame()));
    }
}
