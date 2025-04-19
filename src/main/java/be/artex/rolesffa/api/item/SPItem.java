package be.artex.rolesffa.api.item;

import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class SPItem {
    public static ArrayList<SPItem> registeredItems = new ArrayList<>();

    public abstract @NotNull ItemStack getItemStack();
    public abstract @NotNull TextComponent getDescription();

    public void onClick(PlayerInteractEvent event) {
    }

    public void onHit(EntityDamageByEntityEvent event) {
    }

    public static void registerItem(SPItem item) {
        registeredItems.add(item);
    }

}
