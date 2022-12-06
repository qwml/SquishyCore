package me.jay.squishycore.commandBlocker;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.jay.squishycore.SquishyCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class blockedCommands implements Listener {

    private SquishyCore plugin;
    public blockedCommands(SquishyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void CommandEvent(PlayerCommandPreprocessEvent e) {
        List<String> blockedcommands = plugin.getConfig().getStringList("CommandBlocker.BlockedCommands");
        String error = plugin.getConfig().getString("CommandBlocker.Message");
        Player player = e.getPlayer();
        String blockedcommandssplitcompare = "";
        boolean check = false;
        for (String blockedcommandssplit : blockedcommands) {
            blockedcommandssplitcompare = blockedcommandssplit;
            if (e.getMessage().equalsIgnoreCase(blockedcommandssplitcompare)) {
                check = true;
            }
        }

        if (check) {
            if (!player.isOp()) {
                e.setCancelled(true);
                player.sendMessage(Color(error));
            }
        }
    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }

}
