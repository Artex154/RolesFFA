package be.artex.rolesffa.roles.technique.nagisa;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nagisa extends Role {
    public long coupParaCDT = 0;

    private static final ItemStack STACK = new ItemBuilder(Material.ENDER_PEARL)
            .name(ChatColor.YELLOW + "Nagisa")
            .lore(" ",
                    ChatColor.GRAY + " Nagisa possède " + StringUtils.SPEED_SIGN + ChatColor.YELLOW + " 120% de Vitesse" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Ainsi que son " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant" + ChatColor.GRAY + ".", " ")
            .build();

    @Override
    public String getName() {
        return "Nagisa";
    }

    @Override
    public String getIdentifier() {
        return "nagisa";
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
    public int getBonusSpeed() {
        return 20;
    }

    @Override
    public List<RFItem> getItems() {
        return new ArrayList<>(Collections.singletonList(new CoupParalysant()));
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public void tick(Player player) {
        if (coupParaCDT <= 0)
            return;

        if (coupParaCDT <= 10) {
            coupParaCDT = 0;
            player.sendMessage(Message.generate("Votre " + ChatColor.AQUA + ChatColor.BOLD + "Coup Paralysant" + ChatColor.GRAY + " n'est plus en cooldown."));
        } else
            coupParaCDT -= 10;
    }
}
