package be.artex.rolesffa.api;

import be.artex.rolesffa.api.item.SPItem;
import org.bukkit.inventory.EquipmentSlot;

public class ItemHolder {
    private final SPItem item;
    private final int slot;
    private final EquipmentSlot armor;

    public ItemHolder(SPItem item, EquipmentSlot armor) {
        this.item = item;
        this.slot = 0;
        this.armor = armor;
    }

    public ItemHolder(SPItem item, int slot) {
        this.item = item;
        this.slot = slot;
        this.armor = EquipmentSlot.HAND;
    }

    public ItemHolder(SPItem item) {
        this.item = item;
        this.slot = 0;
        this.armor = EquipmentSlot.HAND;
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
