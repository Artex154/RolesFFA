package be.artex.rolesffa.roles.dps;

import be.artex.rolesffa.Main;
import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.descriptionBuilder.DescriptionBuilder;
import be.artex.rolesffa.api.descriptionBuilder.HoverHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.stats.Effects;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import be.artex.strontium.player.StrontPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Kokushibo extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.DIAMOND_SWORD)
            .name(ChatColor.RED + "Kokushibo")
            .lore(" ",
                    ChatColor.RED + "" + ChatColor.BOLD + " Kokushibo " + ChatColor.GRAY +  "possède " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 105% de Force" + ChatColor.GRAY + ".    ",
                    ChatColor.GRAY + " Il dispose aussi de sa " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.GRAY + ", et d'une " + ChatColor.RED + "progression" + ChatColor.GRAY + " de pouvoirs.", " ")
            .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    public int amountOfHits = 0;
    public int amountOfSaignementHits = 0;
    public int amountOfRequiredHits = 20;
    public int amountOfSaignementRuns = 3;
    public long amountOfTicksPerSaign = 7 * 20L;

    @Override
    public String getName() {
        return "Kokushibo";
    }

    @Override
    public String getIdentifier() {
        return "kokushibo";
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
    public TextComponent getDescription() {
        HoverHolder lame = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Lame de Nichirine" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + ChatColor.BOLD + "Tout les " + ChatColor.RED + ChatColor.BOLD + "20 coups" + ChatColor.WHITE + ", vous infligez un " + ChatColor.RED + ChatColor.BOLD + "saignement" + ChatColor.WHITE + " de " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + "/7 secondes pendant 21 secondes" + ChatColor.WHITE + " qui vous est " + ChatColor.LIGHT_PURPLE + "régénéré" + ChatColor.WHITE + ".      \n "
        );

        HoverHolder evolution = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "Evolution" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.RED + ChatColor.BOLD + "Evolution" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Tout les " + ChatColor.RED + "x coups mis" + ChatColor.WHITE + ", vous gagnez des " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + ".       \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous gardez les " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + " des stades précédents.     \n \n" +
                        ChatColor.RED + ChatColor.BOLD + "    20 coups" + ChatColor.GRAY + " » " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " +5% de Force\n" +
                        ChatColor.RED + ChatColor.BOLD + "    50 coups" + ChatColor.GRAY + " » " + ChatColor.WHITE + "le " + ChatColor.RED + "saignement " + ChatColor.WHITE + "passe à " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + "/4 secondes pendant 16 secondes\n" +
                        ChatColor.RED + ChatColor.BOLD + "    75 coups" + ChatColor.GRAY + " » " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " +5% de Force\n" +
                        ChatColor.RED + ChatColor.BOLD + "    100 coups" + ChatColor.GRAY + " » " + ChatColor.WHITE + "le " + ChatColor.RED + ChatColor.BOLD + "saignement " + ChatColor.WHITE + "arrive tout les " + ChatColor.RED + ChatColor.BOLD + "13 coups " + ChatColor.WHITE + "au lieu de 20.\n"
        );

        DescriptionBuilder description = new DescriptionBuilder(this)
                .passifs(lame, evolution);

        return description.build();
    }

    @Override
    public int getBonusStrength() {
        return 5;
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public void tick(Player player) {
        StrontPlayer strontPlayer = StrontPlayer.getSrontPlayer(player);

        strontPlayer.sendActionBar(ChatColor.DARK_GRAY + "» " + ChatColor.RED + ChatColor.BOLD + "Coups: " + ChatColor.WHITE + amountOfHits + ChatColor.DARK_GRAY + " ▏ " + ChatColor.RED + ChatColor.BOLD + amountOfSaignementHits + ChatColor.WHITE + "/" + amountOfRequiredHits + ChatColor.RED + " saignement" + ChatColor.DARK_GRAY + " «");
    }

    @Override
    public void onHit(Player player, Player damager) {
        amountOfHits++;
        amountOfSaignementHits++;

        if (amountOfSaignementHits >= amountOfRequiredHits) {
            amountOfSaignementHits = 0;

            damager.sendMessage(Message.generate("Vous appliquez un " + ChatColor.RED + "saignement" + ChatColor.GRAY + " sur " + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "."));
            player.sendMessage(Message.generate(ChatColor.RED + "Kokushibo " + ChatColor.GRAY + "vous applique un " + ChatColor.RED + "saignement" + ChatColor.GRAY + "."));

            new BukkitRunnable() {
                int amountOfRuns = 0;

                @Override
                public void run() {
                    amountOfRuns++;

                    StrontPlayer.getSrontPlayer(player).inflictTrueDamage(1);
                    damager.setHealth(damager.getHealth() + 1);

                    if (amountOfRuns == amountOfSaignementRuns)
                        cancel();
                }
            }.runTaskTimer(Main.instance, 0L, amountOfTicksPerSaign);
        }

        switch (amountOfHits) {
            case 20:
                damager.sendMessage(Message.generate("Vous avez atteint le " + ChatColor.RED + "palier 1" + ChatColor.GRAY + ". Vous gagnez " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 5% de Force" + ChatColor.GRAY + "."));
                Effects.get(damager.getUniqueId()).addBonus(RFEffectsType.STRENGTH, 5);
                break;
            case 50:
                damager.sendMessage(Message.generate("Vous avez atteint le " + ChatColor.RED + "palier 2" + ChatColor.GRAY + ". Votre " + ChatColor.RED + "saignement" + ChatColor.GRAY + " passe à " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.GRAY + ChatColor.BOLD + "/4 secondes pendant 16 secondes" + ChatColor.GRAY + "."));
                amountOfSaignementRuns = 4;
                amountOfTicksPerSaign = 4 * 20L;
                break;
            case 75:
                damager.sendMessage(Message.generate("Vous avez atteint le " + ChatColor.RED + "palier 3" + ChatColor.GRAY + ". Vous gagnez " + StringUtils.STRENGTH_SIGN + ChatColor.RED + " 5% de Force" + ChatColor.GRAY + "."));
                Effects.get(damager.getUniqueId()).addBonus(RFEffectsType.STRENGTH, 5);
                break;
            case 100:
                damager.sendMessage(Message.generate("Vous avez atteint le " + ChatColor.RED + "palier final, le palier 4" + ChatColor.GRAY + ". Votre " + ChatColor.RED + "saignement" + ChatColor.GRAY + " arrive désormais seulement tout les " + ChatColor.RED + ChatColor.BOLD + "13 coups" + ChatColor.GRAY + " au lieu de 20."));
                amountOfRequiredHits = 13;
                break;
            default:
                break;
        }
    }
}
