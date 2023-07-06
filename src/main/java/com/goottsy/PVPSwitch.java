package com.goottsy;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static com.goottsy.PVPCommand.*;

public final class PVPSwitch extends JavaPlugin {
    private FileConfiguration config;
    private static PVPSwitch instance;
    private PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();

        commandManager = new PaperCommandManager(this);


        commandManager.registerCommand(new PVPCommand(this));

        registerListener(new PVPListener(this));

        getLogger().info( green("PVPSwitch ha sido Activado"));

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PVPPlaceholder(this).register();
            getLogger().info( green("PVPSwitch: Placeholders Activadas"));
        }
        else {
            getLogger().info( red("PVPSwitch: Placeholders Desactivadas"));
        }

    }

    @Override
    public void onDisable() {
        getLogger().info( red("PVPSwitch ha sido Desactivado"));
    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, instance);
    }

}