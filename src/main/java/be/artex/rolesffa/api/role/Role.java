package be.artex.rolesffa.api.role;

import be.artex.rolesffa.api.item.RFItem;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public abstract class Role {
    public abstract String getName();
    public abstract ItemStack getStack();
    public abstract RoleType getType();
    public abstract int getPlacement();

    public TextComponent getDescription() {
        return new TextComponent("a");
    }

    public int getMaxHealth() {
        return 20;
    }

    public List<RFItem> getItems() {
        return Collections.emptyList();
    }

    public float getSpeed() {
        return 100f;
    }

    public float getStrength() {
        return 100f;
    }

    public float getResistance() {
        return 100f;
    }

    public void register() {
        RoleUtils.addRole(this);
        this.getType().addRole(this);
    }


}
