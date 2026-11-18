package be.artex.rolesffa.roles.dps.chainsaw;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Chainsaw extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.QUARTZ_BLOCK)
            .name(ChatColor.RED + "Chainsaw Man")
            .lore(" ",
                    ChatColor.GRAY + " Kokushibo possède " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 105% de Force" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " En activant votre " + ChatColor.RED + "kill" + ChatColor.GRAY + ", il gagne un " + ChatColor.LIGHT_PURPLE + "coeur" + ChatColor.GRAY + " permanent. (max " + ChatColor.LIGHT_PURPLE + "17 coeurs" + ChatColor.GRAY + ")     ", " ")
            .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    @Override
    public String getName() {
        return "Chainsaw-Man";
    }

    @Override
    public String getIdentifier() {
        return "chainsaw";
    }

    @Override
    public ItemStack getStack() {
        return null;
    }

    @Override
    public RoleType getType() {
        return null;
    }

    @Override
    public TextComponent getDescription() {
        return new TextComponent("a");
    }
}
