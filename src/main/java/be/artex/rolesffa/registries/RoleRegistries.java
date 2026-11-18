package be.artex.rolesffa.registries;

import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.registry.RegistryObject;
import be.artex.rolesffa.registry.RolesRegistries;
import be.artex.rolesffa.roles.dps.Kokushibo;
import be.artex.rolesffa.roles.dps.VPL;
import be.artex.rolesffa.roles.technique.machoireTitan.TitanMachoire;
import be.artex.rolesffa.roles.technique.nagisa.Nagisa;

public class RoleRegistries {
    public static final RegistryObject<Class<? extends Role>> KOKUSHIBO = RolesRegistries.ROLES.registerRole(Kokushibo.class);
    public static final RegistryObject<Class<? extends Role>> VPL = RolesRegistries.ROLES.registerRole(VPL.class);
    public static final RegistryObject<Class<? extends Role>> NAGISA = RolesRegistries.ROLES.registerRole(Nagisa.class);
    public static final RegistryObject<Class<? extends Role>> TITAN_MACHOIRE = RolesRegistries.ROLES.registerRole(TitanMachoire.class);

    public static void registerRoles() {}
}
