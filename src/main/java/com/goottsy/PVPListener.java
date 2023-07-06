package com.goottsy;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static com.goottsy.PVPCommand.pvp;


public class PVPListener implements Listener {
    PVPSwitch instance;
    public PVPListener(PVPSwitch instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (!pvp) {
            if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }




}
