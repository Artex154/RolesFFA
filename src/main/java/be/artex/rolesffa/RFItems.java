package be.artex.rolesffa;

import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.items.RoleSelection;
import be.artex.rolesffa.registry.RegistryObject;
import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.roles.technique.nagisa.CoupParalysant;

public final class RFItems {
    public static final RegistryObject<RFItem> ROLE_SELECTION = RolesRegistries.ITEMS.register("role_selection", new RoleSelection());
    public static final RegistryObject<RFItem> COUP_PARALYSANT = RolesRegistries.ITEMS.register("coup_paralysant", new CoupParalysant());

    public static void registerItems() {} // Simply loads the class
}
