package be.artex.rolesffaold.role.DPS;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.role.Role;
import be.artex.rolesffaold.builder.description.DescriptionBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Knuckles extends Role {
    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .role(this)
                .onHit("vous avez " + ChatColor.AQUA + "10% de chance" + ChatColor.GRAY + " de mettre en " + ChatColor.GOLD + "feu")
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.KNUCKLES;
    }

    @Override
    public void onAssigned(Player player) {
        Role.baseSetup(player, this);

        Role.setPlayerRole(player, this);
    }

    @Override
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Random random = new Random();

        if (random.nextInt(10) == 1)
            event.getEntity().setFireTicks(20*10);
    }

    @Override
    public Float getStrength() {
        return 11.5f;
    }
}
