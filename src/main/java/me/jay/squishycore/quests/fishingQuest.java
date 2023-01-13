package me.jay.squishycore.quests;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class fishingQuest implements Listener {

    private final SquishyCore plugin;
    public fishingQuest(SquishyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerFishingEvent(PlayerFishEvent e) {

        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();


        if (e.getCaught().getType().equals(EntityType.TROPICAL_FISH) || e.getCaught().getType().equals(EntityType.PUFFERFISH) || e.getCaught().getType().equals(EntityType.COD)) {

            PreparedStatement queryStatement;
            PreparedStatement updateStatement;

            try {
                queryStatement = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests WHERE playerUUID=?");
                queryStatement.setString(1, player.getUniqueId().toString());
                ResultSet rows = queryStatement.executeQuery();

                if (rows.next()) {

                    updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET fish=? WHERE playerUUID=?");
                    updateStatement.setString(2, player.getUniqueId().toString());
                    updateStatement.setInt(1, rows.getInt("fish") + 1);
                    updateStatement.executeUpdate();


                    if (rows.getInt("fish") == plugin.getConfig().getInt("Quests.Repeatable.Fishing.AmountOfFish")) {
                        updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET fish=? WHERE playerUUID=?");
                        updateStatement.setString(2, player.getUniqueId().toString());
                        updateStatement.setInt(1, 0);
                        updateStatement.executeUpdate();
                        for (String command : plugin.getConfig().getStringList("Quests.Repeatable.Fishing.Rewards")) {
                            command = command.replace("%player%", player.getName());
                            Bukkit.dispatchCommand(console, command);
                        }
                        String message = plugin.getConfig().getString("Quests.Repeatable.Fishing.Message");
                        message = message.replace("%amountoffish%", String.valueOf(plugin.getConfig().getInt("Quests.Repeatable.Fishing.AmountOfFish")));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                    }

                    plugin.amountoffish = rows.getInt("fish");

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
