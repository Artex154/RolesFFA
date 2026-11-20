package be.artex.rolesffa.roles.technique.machoireTitan;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.stats.Effects;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.roles.technique.machoireTitan.items.EquipementTridimensionnel;
import be.artex.rolesffa.roles.technique.machoireTitan.items.Machoire;
import be.artex.rolesffa.roles.technique.machoireTitan.items.Transformation;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class TitanMachoire extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.GHAST_TEAR)
            .name(ChatColor.YELLOW + "Titan Mâchoire")
            .lore(" ",
                    ChatColor.GRAY + " Le Titan Mâchoire possède l'" + ChatColor.GOLD + ChatColor.BOLD + "Equipement Tridimensionnelle" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Ainsi que sa " + ChatColor.GOLD + ChatColor.BOLD + "Transformation" + ChatColor.GRAY + ".", " ")
            .build();

    public TransformationState transformationState = TransformationState.NOT_TRANSFORMED;
    public long transformationTime = 0;
    public long machoireCDT = 0;
    public long tridiCDT = 0;
    public FishHook hook = null;

    @Override
    public String getName() {
        return "Titan Mâchoire";
    }

    @Override
    public String getIdentifier() {
        return "titan_machoire";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("a");
    }

    @Override
    public List<RFItem> getItems() {
        return Arrays.asList(new Transformation(), new EquipementTridimensionnel(), new Machoire());
    }

    @Override
    public String getSBName() {
        return "T. Mâch";
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public void tick(Player player) {
        if (tridiCDT > 0) {
            if (tridiCDT <= 10) {
                tridiCDT = 0;
                player.sendMessage(Message.generate("Votre " + ChatColor.GOLD + ChatColor.BOLD + "Equipement Tridimensionel" + ChatColor.GRAY + " n'est plus en cooldown."));
            } else
                tridiCDT -= 10;
        }

        if (transformationState == TransformationState.TRANSFORMED) {
            if (transformationTime == 0)
                return;

            if (transformationTime <= 10) {
                transformationTime = 50 * 20L;
                transformationState = TransformationState.NOT_TRANSFORMED;

                player.setMaxHealth(20);
                Effects.get(player.getUniqueId()).addBonus(RFEffectsType.SPEED, -15);

                player.sendMessage(Message.generate("Votre " + ChatColor.GOLD + ChatColor.BOLD + "transformation " + ChatColor.GRAY + "cesse."));
            } else {
                transformationTime -= 10;

                if (machoireCDT == 0)
                    return;

                if (machoireCDT <= 10) {
                    machoireCDT = 0;
                    player.sendMessage(Message.generate("Votre " + ChatColor.AQUA + ChatColor.BOLD + "Mâchoire" + ChatColor.GRAY + " n'est plus en cooldown."));
                } else
                    machoireCDT -= 10;
            }
        } else {
            if (transformationTime <= 0)
                return;

            if (transformationTime <= 10) {
                transformationTime = 0;
                player.sendMessage(Message.generate("Votre " + ChatColor.GOLD + ChatColor.BOLD + "transformation" + ChatColor.GRAY + " n'est plus en cooldown."));
            } else
                transformationTime -= 10;
        }
    }
}
