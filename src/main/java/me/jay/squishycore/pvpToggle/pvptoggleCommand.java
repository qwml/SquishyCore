package me.jay.squishycore.pvpToggle;

import me.jay.squishycore.SquishyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class pvptoggleCommand implements CommandExecutor {

    private final SquishyCore plugin;
    public pvptoggleCommand(SquishyCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (command.getName().equalsIgnoreCase("pvp")){

            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("toggle")){

                if (plugin.toggledON.containsKey(player.getUniqueId())){

                    plugin.toggledON.remove(player.getUniqueId());
                    String message = plugin.getConfig().getString("pvptoggle.Messages.Toggle");
                    message = message.replace("%status%", "off");
                    player.sendMessage(Color(message));

                }else{

                    plugin.toggledON.put(player.getUniqueId(), "true");
                    String message = plugin.getConfig().getString("pvptoggle.Messages.Toggle");
                    message = message.replace("%status%", "on");
                    player.sendMessage(Color(message));

                }

            }

        }


        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
