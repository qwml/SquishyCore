package me.jay.squishycore.pvpToggle;

import me.jay.squishycore.SquishyCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class pvptoggleEvent implements Listener {

    private final SquishyCore plugin;
    public pvptoggleEvent(SquishyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent e) {

        if (e.getEntity().getType().equals(EntityType.PLAYER)) {

            if (e.getDamager().getType() == EntityType.PLAYER){

                if (!plugin.toggledON.containsKey(e.getDamager().getUniqueId()) || !plugin.toggledON.containsKey(e.getEntity().getUniqueId())) {


                    e.setCancelled(true);
                    e.getDamager().sendMessage(Color(plugin.getConfig().getString("pvptoggle.Messages.HitMessage")));


                }
            }

        }
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent e){

        if (plugin.toggledON.containsKey(e.getPlayer().getUniqueId())){

            plugin.toggledON.remove(e.getPlayer().getUniqueId());

        }

    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

}
