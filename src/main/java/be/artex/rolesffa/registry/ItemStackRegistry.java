package be.artex.rolesffa.registry;

import be.artex.rolesffa.api.item.RFItem;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Specialized Registry for item-based elements.
 */
public class ItemStackRegistry extends SimpleRegistry<RFItem> {
    private final Map<ItemStack, RFItem> stackMap;

    public ItemStackRegistry(String key) {
        super(key);
        this.stackMap = new ConcurrentHashMap<>();
    }

    @Override
    public RegistryObject<RFItem> register(String key, RFItem value) {
        RegistryObject<RFItem> object = super.register(key, value);

        ItemStack stack = value.getItem();
        this.stackMap.put(stack, value);

        return object;
    }

    public Optional<RFItem> getItemFromStack(ItemStack stack) {
        return Optional.ofNullable(this.stackMap.get(stack));
    }
}
