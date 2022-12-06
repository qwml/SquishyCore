package me.jay.squishycore.greetingsMessages;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class joinleaveEvent implements Listener {

    Plugin plugin = SquishyCore.getPlugin(SquishyCore.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (player.hasPlayedBefore()) {
            if (e.getPlayer().hasPermission("squishycore.staffjoin")) {
                if (plugin.getConfig().getString("JoinMessages.Messages.Staff.SilentJoin").equals("false")) {
                    e.setJoinMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Staff.Join").replace("%player%", player.getName())));
                }else if (plugin.getConfig().getString("JoinMessages.Messages.Staff.SendToStaff").equals("true")){
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("squishycore.staffjoin")) {
                            p.sendMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Staff.Join").replace("%player%", player.getName())));
                            return;
                        }
                    }
                }else{
                    return;
                }
            } else if (e.getPlayer().hasPermission("squishycore.donatorjoin")) {
                e.setJoinMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Donator.Join").replace("%player%", player.getName())));
            } else {
                e.setJoinMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Player.Join").replace("%player%", player.getName())));
            }
        }else{
            e.setJoinMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Player.Welcome").replace("%player%", player.getName())));
        }

        Bukkit.broadcastMessage(Color(e.getJoinMessage()));


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player player = e.getPlayer();

        if (e.getPlayer().hasPermission("squishycore.staffjoin")){
            if (plugin.getConfig().getString("JoinMessages.Messages.Staff.SilentJoin").equals("false")) {
                e.setQuitMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Staff.Leave").replace("%player%", player.getName())));
            }else{
                return;
            }
        }else if (e.getPlayer().hasPermission("squishycore.donatorjoin")){
            e.setQuitMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Donator.Leave").replace("%player%", player.getName())));
        }else{
            e.setQuitMessage(Color(plugin.getConfig().getString("JoinMessages.Messages.Player.Leave").replace("%player%", player.getName())));

        }

        Bukkit.broadcastMessage(Color(e.getQuitMessage()));

    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
