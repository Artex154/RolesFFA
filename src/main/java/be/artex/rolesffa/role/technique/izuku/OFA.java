package be.artex.rolesffa.role.technique.izuku;

import be.artex.rolesffa.Stacks;
import be.artex.rolesffa.api.item.SPItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;

public class OFA extends SPItem {
    @Override
    public ItemStack getItemStack() {
        return Stacks.OFA;
    }

    @Override
    public TextComponent getDescription() {
        return null;
    }
}
