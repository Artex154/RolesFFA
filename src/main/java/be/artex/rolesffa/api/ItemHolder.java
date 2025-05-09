package be.artex.rolesffa.api;

import be.artex.rolesffa.api.item.SPItem;
import org.bukkit.inventory.EquipmentSlot;

public class ItemHolder {
    private final SPItem item;
    private final int slot;
    private final EquipmentSlot armor;

    private ItemHolder(SPItem item, int slot, EquipmentSlot armor) {
        this.item = item;
        this.slot = slot;
        this.armor = armor;
    }

    public static ItemHolder asArmor(SPItem item, EquipmentSlot slot) {
        return new ItemHolder(item, 0, slot);
    }

    public static ItemHolder inHand(SPItem item, int slot) {
        return new ItemHolder(item, slot, EquipmentSlot.HAND);
    }

    public static ItemHolder inHand(SPItem item) {
        return new ItemHolder(item, 0, EquipmentSlot.HAND);
    }

    public SPItem getItem() {
        return item;
    }

    public int getSlot() {
        return slot;
    }

    public EquipmentSlot getArmor() {
        return armor;
    }
}
