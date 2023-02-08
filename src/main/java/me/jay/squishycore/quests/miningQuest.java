//package me.jay.squishycore.quests;
//
//import me.jay.squishycore.SquishyCore;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Location;
//import org.bukkit.command.ConsoleCommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.EventPriority;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.scheduler.BukkitScheduler;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//
//
//public class miningQuest implements Listener {
//
//    private final SquishyCore plugin;
//    public miningQuest(SquishyCore plugin){
//        this.plugin = plugin;
//    }
//
//
//
//    @EventHandler
//    public void PlayerMineEvent(BlockBreakEvent e){
//
//        Player player = e.getPlayer();
//        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//
//
//
//        PreparedStatement queryStatement;
//        PreparedStatement updateStatement;
//
//        try{
//            queryStatement = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests WHERE playerUUID=?");
//            queryStatement.setString(1, player.getUniqueId().toString());
//            ResultSet rows = queryStatement.executeQuery();
//
//            if(rows.next()){
//
//                updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET mining=? WHERE playerUUID=?");
//                updateStatement.setString(2, player.getUniqueId().toString());
//                updateStatement.setInt(1, rows.getInt("mining")+1);
//                updateStatement.executeUpdate();
//
//
//                if (rows.getInt("mining") == plugin.getConfig().getInt("Quests.Repeatable.Mining.AmountOfBlocks")){
//                    updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET mining=? WHERE playerUUID=?");
//                    updateStatement.setString(2, player.getUniqueId().toString());
//                    updateStatement.setInt(1, 0);
//                    updateStatement.executeUpdate();
//                    for (String command : plugin.getConfig().getStringList("Quests.Repeatable.Mining.Rewards")) {
//                        command = command.replace("%player%", player.getName());
//                        Bukkit.dispatchCommand(console, command);
//                    }
//                    String message = plugin.getConfig().getString("Quests.Repeatable.Mining.Message");
//                    message = message.replace("%amountofblocks%", String.valueOf(plugin.getConfig().getInt("Quests.Repeatable.Mining.AmountOfBlocks")));
//                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
//                }
//
//                plugin.amountofblocks = rows.getInt("mining");
//
//            }
//
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }
//
//
//
//
//
//
//    }
//
//
//
//
//
//
//}
//