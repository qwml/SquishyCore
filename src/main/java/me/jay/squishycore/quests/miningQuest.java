package me.jay.squishycore.quests;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class miningQuest implements Listener {

    private final SquishyCore plugin;
    public miningQuest(SquishyCore plugin){
        this.plugin = plugin;
    }



    @EventHandler
    public void PlayerMineEvent(BlockBreakEvent e) throws SQLException {

        Player player = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();



        PreparedStatement queryStatement;
        PreparedStatement updateStatement;

        try{
            queryStatement = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests");
            ResultSet rows = queryStatement.executeQuery();

            while(rows.next()){

                if (rows.getString("playerUUID").equalsIgnoreCase(player.getUniqueId().toString())){

                    updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET mining = ? WHERE playerUUID = ?");
                    updateStatement.setString(2, player.getUniqueId().toString());

                    if (rows.getInt("mining") == plugin.getConfig().getInt("Quests.Repeatable.Mining.AmountOfBlocks")){
                        updateStatement.setInt(1, 0);
                        for (String command : plugin.getConfig().getStringList("Quests.Repeatable.Mining.Rewards")) {
                            Bukkit.dispatchCommand(console, command);
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Quests.Repeatable.Mining.Message")));
                    }else{
                        updateStatement.setInt(1, rows.getInt("mining") + 1);
                    }

                }
                player.sendMessage("done " + rows.getInt("mining"));

            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }






    }






}
