package me.jay.squishycore.quests;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class farmingQuest implements Listener {


    private final SquishyCore plugin;
    public farmingQuest(SquishyCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerFarmingEvent(PlayerHarvestBlockEvent e){


        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();




        PreparedStatement queryStatement;
        PreparedStatement updateStatement;

            try {
                queryStatement = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests WHERE playerUUID=?");
                queryStatement.setString(1, player.getUniqueId().toString());
                ResultSet rows = queryStatement.executeQuery();

                if (rows.next()) {

                    updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET farming=? WHERE playerUUID=?");
                    updateStatement.setString(2, player.getUniqueId().toString());
                    updateStatement.setInt(1, rows.getInt("farming") + 1);
                    updateStatement.executeUpdate();


                    if (rows.getInt("farming") == plugin.getConfig().getInt("Quests.Repeatable.Farming.AmountOfCrops")) {
                        updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET farming=? WHERE playerUUID=?");
                        updateStatement.setString(2, player.getUniqueId().toString());
                        updateStatement.setInt(1, 0);
                        updateStatement.executeUpdate();
                        for (String command : plugin.getConfig().getStringList("Quests.Repeatable.Farming.Rewards")) {
                            command = command.replace("%player%", player.getName());
                            Bukkit.dispatchCommand(console, command);
                        }
                        String message = plugin.getConfig().getString("Quests.Repeatable.Farming.Message");
                        message = message.replace("%amountofcrops%", String.valueOf(plugin.getConfig().getInt("Quests.Repeatable.Farming.AmountOfCrops")));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                    }

                    plugin.amountoffish = rows.getInt("farming");

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

}
