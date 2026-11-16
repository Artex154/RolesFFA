package be.artex.rolesffa.roles.dps;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.helper.StringHelper;
import be.artex.strontium.itemBuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Kokushibo extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.DIAMOND_SWORD)
            .name(ChatColor.RED + "Kokushibo")
            .lore(" ",
                    ChatColor.GRAY + " Kokushibo possède " + StringHelper.STRENGTH_SIGN + ChatColor.RED + " 110% de Force" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " A chaque " + ChatColor.RED + "kill" + ChatColor.GRAY + ", il gagne " + ChatColor.RED + "1❤ permanent" + ChatColor.GRAY + ". (max " + ChatColor.RED + "17❤" + ChatColor.GRAY + ")     ", " ")
            .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    @Override
    public String getName() {
        return "Kokushibo";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.DPS;
    }

    @Override
    public int getPlacement() {
        return 20;
    }

    @Override
    public int getBonusStrength() {
        return 10;
    }

    @Override
    public void onPlayerKill(Player killer, Player player) {
        if (killer.getMaxHealth() >= 34)
            return;

        killer.sendMessage(Message.generate("Vous gagnez " + ChatColor.RED + "1❤ permanent" + ChatColor.GRAY + " pour avoir tué " + ChatColor.DARK_AQUA + player.getName() + ChatColor.GRAY + "."));

        killer.setMaxHealth(killer.getMaxHealth() + 2);
        killer.setHealth(killer.getMaxHealth());
    }
}
