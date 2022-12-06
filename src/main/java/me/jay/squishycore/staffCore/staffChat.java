package me.jay.squishycore.staffCore;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class staffChat implements CommandExecutor {

    Plugin plugin = SquishyCore.getPlugin(SquishyCore.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("sc")){

                if (player.hasPermission("squishycore.staffchat")) {

                    if (args.length <= 0){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cError&8] &cUsage: /sc (message)."));
                    }else{

                        String message = "";
                        for (int i = 0; i < args.length; i++){

                            message = message + args[i] + " ";

                        }

                        for (Player p : Bukkit.getOnlinePlayers()){
                            if (p.hasPermission("squishycore.staffchat")){
                                String format = plugin.getConfig().getString("StaffCore.StaffChat.Format");
                                format = format.replace("%player%", player.getName()).replace("%s", message);
                                p.sendMessage(Color(format));
                            }
                        }

                    }

                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cError&8] &cNo Permission."));
                }
            }

        }


        return true;
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
