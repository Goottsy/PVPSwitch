package com.goottsy;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import static com.goottsy.PVPCommand.*;


public class PVPPlaceholder extends PlaceholderExpansion {

    private PVPSwitch plugin;


    public PVPPlaceholder(PVPSwitch plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }
    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Goottsy";
    }

    @Override
    public String getIdentifier(){
        return "pvpswitch";
    }


    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }


    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("pvp")) {
            return PVPCommand.pvp ? red(pvpe) : green(pvpd);
        }
        return null;
    }
}