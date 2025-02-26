package be.artex.rolesffa.role.heros.shoto;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.Team;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleUtils;
import be.artex.rolesffa.builder.description.DescriptionBuilder;
import be.artex.rolesffa.role.heros.shoto.items.Fire;
import be.artex.rolesffa.role.heros.shoto.items.Ice;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Shoto extends Role {
    @Override
    public String getName() {
        return ChatColor.YELLOW + "Shoto";
    }

    @Override
    public TextComponent getDescription() {
        return new DescriptionBuilder(getName())
                .effect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1,0))
                .item(new Ice(), new Fire())
                .build();
    }

    @Override
    public ItemStack getItemStack() {
        return Stacks.SHOTO;
    }

    @Override
    public Team getCamp() {
        return Team.TECHNIQUE;
    }

    @Override
    public int getPlacement() {
        return 13;
    }

    @Override
    public void onAssigned(Player player) {
        RoleUtils.baseSetup(player, this);

        RoleUtils.setPlayerRole(player.getUniqueId(), this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));

        player.getInventory().addItem(Stacks.ICE);
        player.getInventory().addItem(Stacks.FIRE);
    }
}
