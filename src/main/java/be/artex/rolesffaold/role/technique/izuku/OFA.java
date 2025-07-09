package be.artex.rolesffaold.role.technique.izuku;

import be.artex.rolesffaold.Stacks;
import be.artex.rolesffaold.api.item.SPItem;
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
