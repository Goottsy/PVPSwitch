package com.goottsy;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import sun.jvm.hotspot.ui.tree.CStringTreeNodeAdapter;

@CommandAlias("pvp")
@CommandPermission("pvpswitch.pvp")
public class PVPCommand extends BaseCommand {

    private PVPSwitch instance;
    private FileConfiguration config;
    public static boolean pvp;
    public float volume, pitch;
    public Sound sound;

    public static String pvpe, pvpd;



    public PVPCommand(PVPSwitch instance) {
        this.instance = instance;
        this.config = instance.getConfig();
        pvp = config.getBoolean("pvp");
        volume = (float) config.getDouble("sound.volume");
        pitch = (float) config.getDouble("sound.pitch");
        sound = Sound.valueOf(config.getString("sound.name"));
        pvpe = config.getString("pvp-text.enabled");
        pvpd = config.getString("pvp-text.disabled");
    }

    @Subcommand("pvp")
    @CommandAlias("pvp")
    @CommandPermission("pvpswitch.pvp")
    @CommandCompletion("true|false")
    public void pvp(CommandSender sender, boolean bool) {
        if (bool) {
            pvp = true;
            config.set("pvp", true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                Location loc = p.getLocation();

                Sound sound = Sound.valueOf(config.getString("sound.name", "ENTITY_ELDER_GUARDIAN_CURSE"));
                p.playSound(loc, sound, volume, pitch);
                p.sendMessage(darkred("     - ") + red("¡El PVP ha sido activado!"));
            }
        } else {
            pvp = false;
            config.set("pvp", false);
            for (Player p : Bukkit.getOnlinePlayers()) {
                Location loc = p.getLocation();

                p.playSound(loc, sound, volume, pitch);
                p.sendMessage(darkgreen("     - ") + green("¡El PVP ha sido desactivado!"));
            }
        }
        instance.saveConfig();
    }

    @Subcommand("reload")
    @CommandAlias("pvpreload")
    @CommandPermission("pvpswitch.pvpreload")
    public void reloadConfig(CommandSender sender) {
        instance.reloadConfig();
        pvp = config.getBoolean("pvp");
        volume = (float) config.getDouble("sound.volume");
        pitch = (float) config.getDouble("sound.pitch");
        sound = Sound.valueOf(config.getString("sound.name"));
        pvpe = config.getString("pvp-text.enabled");
        pvpd = config.getString("pvp-text.disabled");

        sender.sendMessage(green("¡La configuración se ha recargado correctamente!"));
        sender.sendMessage(green("PVP: ") + yellow(String.valueOf(pvp)));
        sender.sendMessage(green("Sound: ") + yellow(String.valueOf(sound)));
        sender.sendMessage(green("Volumen: ") + yellow(String.valueOf(volume)));
        sender.sendMessage(green("Pitch: ") + yellow(String.valueOf(pitch)));

        sender.sendMessage(green("PVP: ") + yellow(String.valueOf(pvpe)));
        sender.sendMessage(green("PVP: ") + yellow(String.valueOf(pvpd)));
    }

    @Subcommand("help")
    @CommandAlias("pvphelp")
    @CommandPermission("pvpswitch.pvp")
    public void help(CommandSender sender) {
        sender.sendMessage(yellow("=== Comandos de PVP SWITCH ==="));
        sender.sendMessage(green("/pvp <true|false>"));
        sender.sendMessage(yellow("  - Activa o desactiva el PvP en el servidor."));
        sender.sendMessage(yellow("    Requiere el permiso: pvpswitch.pvp"));
        sender.sendMessage(green("/pvp reload"));
        sender.sendMessage(yellow("  - Recarga la configuración del plugin."));
        sender.sendMessage(yellow("    Requiere el permiso: pvpswitch.pvpreload"));
        sender.sendMessage(green("/pvp help"));
        sender.sendMessage(yellow("  - Muestra este mensaje de ayuda."));
    }

    public static String green(String text) {
        return ChatColor.GREEN + text + ChatColor.RESET;
    }

    public static String yellow(String text) {
        return ChatColor.YELLOW + text + ChatColor.RESET;
    }

    public static String darkgreen(String text) {
        return ChatColor.DARK_GREEN + text + ChatColor.RESET;
    }

    public static String white(String text) {
        return ChatColor.WHITE + text + ChatColor.RESET;
    }

    public static String red(String text) {
        return ChatColor.RED + text + ChatColor.RESET;
    }

    public static String darkred(String text) {
        return ChatColor.DARK_RED + text + ChatColor.RESET;
    }
}
