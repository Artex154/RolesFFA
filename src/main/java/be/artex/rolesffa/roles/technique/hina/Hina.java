package be.artex.rolesffa.roles.technique.hina;

import be.artex.rolesffa.api.Message;
import be.artex.rolesffa.api.descriptionBuilder.DescriptionBuilder;
import be.artex.rolesffa.api.descriptionBuilder.HoverHolder;
import be.artex.rolesffa.api.role.Role;
import be.artex.rolesffa.api.role.RoleType;
import be.artex.rolesffa.api.stats.Effects;
import be.artex.rolesffa.api.stats.RFEffectsType;
import be.artex.rolesffa.api.weather.WeatherType;
import be.artex.rolesffa.util.StringUtils;
import be.artex.strontium.itemBuilder.ItemBuilder;
import be.artex.strontium.player.StrontPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;

public class Hina extends Role {
    private static final ItemStack STACK = new ItemBuilder(Material.WATER_BUCKET)
            .name(ChatColor.YELLOW + "Hina")
            .lore(" ",
                    ChatColor.YELLOW + "" + ChatColor.BOLD + " Hina " + ChatColor.GRAY + "peut transformer la " + ChatColor.BLUE + ChatColor.BOLD + "Météo " + ChatColor.GRAY + "à sa guise.    ", " ")
            .build();

    public Location zoneCenter = null;
    public long zoneTicksLeft = 0;
    public WeatherType zoneWeather = WeatherType.RAINING;
    public boolean isInZone = true;

    public WeatherType appliedWeather = WeatherType.RAINING;
    public double heartsLost = 0;

    public long sunCDT = 0;
    public long thunderCDT = 0;
    public long djCDT = 0;

    public int amountOfSunHits = 0;

    @Override
    public String getName() {
        return "Hina";
    }

    @Override
    public String getIdentifier() {
        return "hina";
    }

    @Override
    public ItemStack getStack() {
        return STACK;
    }

    @Override
    public RoleType getType() {
        return RoleType.TECHNIQUE;
    }

    @Override
    public TextComponent getDescription() {
        TextComponent meteo = new TextComponent(ChatColor.GRAY + "Prêtresse du temps: " + ChatColor.WHITE + "Vous êtes maudit. La " + ChatColor.BLUE + ChatColor.BOLD + "pluie" + ChatColor.WHITE + " vous suit. Sa position s'actualise " + ChatColor.BOLD + "toutes les 30 secondes" + ChatColor.WHITE + " et possède un rayon de " + ChatColor.YELLOW + "30x30" + ChatColor.WHITE + " autour de vous. Vous êtes le seul à pouvoir voir la limite du rayon. En dehors du rayon, il s'agira de la " + ChatColor.BLUE + "météo " + ChatColor.WHITE + "de base.");
        HoverHolder aMeteo = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + ChatColor.BOLD + "Avantages Météo" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.BLUE + ChatColor.BOLD + "Avantages Météo" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "En fonction de la " + ChatColor.BLUE + "météo " + ChatColor.BOLD + "invoquée" + ChatColor.WHITE + ", vous gagnez des " + ChatColor.GREEN + "bonus" + ChatColor.WHITE + ".\n" +
                        ChatColor.BLUE + ChatColor.BOLD + "    " + WeatherType.RAINING.getName() + ChatColor.GRAY + "   ²» " + StringUtils.SPEED_SIGN + ChatColor.YELLOW + " +20% de Vitesse " + ChatColor.WHITE + "ainsi qu'un " + ChatColor.GOLD + ChatColor.BOLD + "double jump " + ChatColor.WHITE + "disponible toutes les 20 secondes.\n" +
                        ChatColor.GOLD + ChatColor.BOLD + "    " + WeatherType.SUNNY.getName() + ChatColor.GRAY + " » " + ChatColor.WHITE + "Une " + ChatColor.LIGHT_PURPLE + "régénération" + ChatColor.WHITE + " à hauteur de " + ChatColor.RED + ChatColor.BOLD + "0,5❤" + ChatColor.WHITE + ChatColor.BOLD + " toutes les 5 secondes" + ChatColor.WHITE + ",\n" +
                        ChatColor.WHITE + "                          ainsi qu'un effet de " + ChatColor.GOLD + ChatColor.BOLD + "fire aspect" + ChatColor.WHITE + ChatColor.BOLD + " tout les 5 coups" + ChatColor.WHITE + ".\n"
        );

        HoverHolder soleil = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "Soleil" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.GOLD + ChatColor.BOLD + "Soleil" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous pouvez invoquer le " + ChatColor.GOLD + ChatColor.BOLD + "soleil" + ChatColor.WHITE + ChatColor.BOLD + " pendant 30 secondes " + ChatColor.WHITE + "à un " + ChatColor.BOLD + "rayon de" + ChatColor.YELLOW + ChatColor.BOLD + " 50x50" + ChatColor.WHITE + ".   \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Elle remplacera " + ChatColor.BOLD + "votre zone" + ChatColor.WHITE + ".     \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Cependant, effectuer cette action vous coûte " + ChatColor.RED + ChatColor.BOLD + "1❤ permanent" + ChatColor.WHITE + " récupéré à votre prochain" + ChatColor.RED + " kill" + ChatColor.WHITE + ".       \n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Cooldown: " + ChatColor.YELLOW + ChatColor.BOLD + "1 minute\n"
        );

        HoverHolder eclair = new HoverHolder(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "Eclairs" + ChatColor.DARK_GRAY + "]",
                " \n " + ChatColor.DARK_GRAY + "    [" + ChatColor.YELLOW + ChatColor.BOLD + "Eclairs" + ChatColor.DARK_GRAY + "]\n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous pouvez seulement effectuer cette action dans un temps " + ChatColor.BOLD + ChatColor.BLUE + "pluvieux" + ChatColor.WHITE + ".       \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Vous pouvez invoquer des " + ChatColor.YELLOW + ChatColor.BOLD + "éclairs" + ChatColor.WHITE + " sur un " + ChatColor.YELLOW + "bloc" + ChatColor.WHITE + " ou " + ChatColor.YELLOW + "joueur" + ". \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Ceux-ci effectuent une " + ChatColor.GOLD + "explosion" + ChatColor.WHITE + " et infligent " + ChatColor.RED + "2❤" + ChatColor.WHITE + " aux joueurs proches." +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Cependant, invoquer un " + ChatColor.YELLOW + "éclair" + ChatColor.WHITE + " vous coûte " + ChatColor.RED + ChatColor.BOLD + "1❤ permanent" + ChatColor.WHITE + " récupéré à votre prochain" + ChatColor.RED + " kill" + ChatColor.WHITE + ".       \n \n" +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " - " + ChatColor.WHITE + "Cooldown: " + ChatColor.YELLOW + ChatColor.BOLD + "1 minute\n"
        );

        DescriptionBuilder description = new DescriptionBuilder(this).passifs(meteo, aMeteo.toComponent()).activables(soleil, eclair);

        return description.build();
    }

    @Override
    public void onPlayerKill(Player killer, Player player) {
        if (heartsLost <= 0)
            return;

        player.sendMessage(Message.generate("Vous avez effectué un " + ChatColor.RED + "kill" + ChatColor.WHITE + ", vous récupérez vos " + ChatColor.RED + heartsLost / 2 + "❤ permanents" + ChatColor.WHITE + "."));
        player.setMaxHealth(player.getMaxHealth() + heartsLost);
    }

    @Override
    public void onHit(Player player, Player damager) {
        if (appliedWeather == WeatherType.SUNNY) {
            amountOfSunHits++;

            if (amountOfSunHits == 3) {
                player.setFireTicks(800);
                amountOfSunHits = 0;
            }
        }
    }

    @Override
    public void onFlightToggle(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (appliedWeather != WeatherType.RAINING)
            return;

        if (djCDT > 0) {
            player.sendMessage(Message.cooldownTimeLeft(djCDT / 20));
            return;
        }

        player.setVelocity(player.getLocation().getDirection().multiply(1.2).setY(2));
        player.getWorld().playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1f, 1f);

        djCDT = 7 * 20L;
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    @Override
    public void tick(Player player) {
        if (zoneTicksLeft <= 0) {
            zoneTicksLeft = 30 * 20L;
            zoneCenter = player.getLocation();
            zoneWeather = WeatherType.RAINING;
            isInZone = true;
        }

        if (sunCDT > 0) {
            if (sunCDT <= 10)
                sunCDT = 0;
            else
                sunCDT -= 10;
        }

        if (thunderCDT > 0) {
            if (thunderCDT <= 10)
                thunderCDT = 0;
            else
                thunderCDT -= 10;
        }

        if (djCDT > 0) {
            if (djCDT <= 10)
                djCDT = 0;
            else
                djCDT -= 10;
        }

        if (isInZone() != isInZone) {
            isInZone = !isInZone;

            if (isInZone) {
                applyWeatherEffects(zoneWeather, player);
            } else {
                removeAppliedWeatherEffects(player);
            }
        }

        String aBar = ChatColor.DARK_GRAY + "» " + ChatColor.WHITE + "Météo appliquée: " + appliedWeather.getColor() + appliedWeather.getName() + ChatColor.DARK_GRAY + " ▏ " + ChatColor.GOLD + ChatColor.BOLD;

        if (appliedWeather == WeatherType.RAINING) {
            String ready = ChatColor.WHITE + "" + djCDT / 20 + " secondes";

            if (djCDT == 0)
                ready = ChatColor.GREEN + "prêt";

            aBar += "Double Jump: " + ready + ChatColor.DARK_GRAY + " «";
        } else if (appliedWeather == WeatherType.SUNNY) {
            aBar += "Coups: " +  amountOfSunHits + ChatColor.WHITE + "/3" + ChatColor.DARK_GRAY + " «";
        }

        StrontPlayer.getSrontPlayer(player).sendActionBar(aBar);
    }

    public boolean isInZone() {
        int radius = 30;

        if (zoneWeather == WeatherType.SUNNY)
            radius += 20;

        return true;
    }

    public void applyWeatherEffects(WeatherType type, Player player) {
        removePlayerRole(player);

        appliedWeather = type;

        if (appliedWeather == WeatherType.RAINING)
            Effects.get(player.getUniqueId()).addBonus(RFEffectsType.SPEED, 20);
    }

    public void removeAppliedWeatherEffects(Player player) {
        if (appliedWeather == WeatherType.RAINING)
            Effects.get(player.getUniqueId()).addBonus(RFEffectsType.SPEED, -20);

        appliedWeather = null;
    }
}
