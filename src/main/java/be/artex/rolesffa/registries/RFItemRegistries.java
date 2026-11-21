package be.artex.rolesffa.registries;

import be.artex.rolesffa.api.item.RFItem;
import be.artex.rolesffa.roles.tank.gaara.Shukaku;
import be.artex.rolesffa.roles.technique.machoireTitan.items.EquipementTridimensionnel;
import be.artex.rolesffa.items.RoleSelection;
import be.artex.rolesffa.registry.RegistryObject;
import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.roles.technique.machoireTitan.items.Machoire;
import be.artex.rolesffa.roles.technique.machoireTitan.items.Transformation;

public final class RFItemRegistries {
    public static final RegistryObject<RFItem> ROLE_SELECTION = RolesRegistries.ITEMS.register("role_selection", new RoleSelection());
    public static final RegistryObject<RFItem> EQUIPEMENT_TRIDIMENSIONNELLE = RolesRegistries.ITEMS.register("equipement_tridimensionnelle", new EquipementTridimensionnel());
    public static final RegistryObject<RFItem> MACHOIRE_TRANSFORMATION = RolesRegistries.ITEMS.register("machoire_transformation", new Transformation());
    public static final RegistryObject<RFItem> MACHOIRE = RolesRegistries.ITEMS.register("machoire", new Machoire());
    public static final RegistryObject<RFItem> SHUKAKU = RolesRegistries.ITEMS.register("shukaku", new Shukaku());

    public static void registerItems() {}
}
