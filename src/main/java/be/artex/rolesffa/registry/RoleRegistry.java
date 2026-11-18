package be.artex.rolesffa.registry;
import be.artex.rolesffa.api.role.Role;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoleRegistry extends SimpleRegistry<Class<? extends Role>> {
    private final Map<ItemStack, Class<? extends Role>> roleMap;

    public RoleRegistry(String key) {
        super(key);
        roleMap = new ConcurrentHashMap<>();
    }

    public RegistryObject<Class<? extends Role>> registerRole(@NotNull Class<? extends Role> role) {
        Role r = getRole(role);
        RegistryObject<Class<? extends Role>> registryObject = super.register(r.getIdentifier(), role);

        roleMap.put(r.getStack(), role);
        r.getType().addRole(r);

        return registryObject;
    }

    @Nullable
    public Role getRoleFromMenuStack(ItemStack stack) {
        return getRole(this.roleMap.get(stack));
    }

    @Nullable
    public static Role getRole(Class<? extends Role> clazz) {
        if (clazz == null)
            return null;

        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Cannot instantiate " + clazz.getName(), e);
        }
    }
}
