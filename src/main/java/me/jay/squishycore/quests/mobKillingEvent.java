//package me.jay.squishycore.quests;
//
//import me.jay.squishycore.SquishyCore;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.command.ConsoleCommandSender;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.LivingEntity;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.event.entity.EntityDeathEvent;
//import org.jetbrains.annotations.NotNull;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Objects;
//
//public class mobKillingEvent implements Listener {
//
//
//    private final SquishyCore plugin;
//    public mobKillingEvent(SquishyCore plugin){
//        this.plugin = plugin;
//    }
//
//    @EventHandler
//    public void PlayerMobKillEvent(EntityDeathEvent e) {
//
//            if (e.getEntity().getType() == EntityType.ZOMBIE || e.getEntity().getType() == EntityType.CREEPER || e.getEntity().getType() == EntityType.SKELETON) {
//
//                Player player = e.getEntity().getKiller();
//
//                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//
//
//                PreparedStatement queryStatement;
//                PreparedStatement updateStatement;
//
//                try {
//                    queryStatement = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests WHERE playerUUID=?");
//                    queryStatement.setString(1, player.getUniqueId().toString());
//                    ResultSet rows = queryStatement.executeQuery();
//
//                    if (rows.next()) {
//
//                        updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET mobskilled=? WHERE playerUUID=?");
//                        updateStatement.setString(2, player.getUniqueId().toString());
//                        updateStatement.setInt(1, rows.getInt("mobskilled") + 1);
//                        updateStatement.executeUpdate();
//
//
//                        if (rows.getInt("mobskilled") == plugin.getConfig().getInt("Quests.Repeatable.MobKiller.AmountOfMobs")) {
//                            updateStatement = plugin.DB.getConnection().prepareStatement("UPDATE quests SET mobskilled=? WHERE playerUUID=?");
//                            updateStatement.setString(2, player.getUniqueId().toString());
//                            updateStatement.setInt(1, 0);
//                            updateStatement.executeUpdate();
//                            for (String command : plugin.getConfig().getStringList("Quests.Repeatable.MobKiller.Rewards")) {
//                                command = command.replace("%player%", player.getName());
//                                Bukkit.dispatchCommand(console, command);
//                            }
//                            String message = plugin.getConfig().getString("Quests.Repeatable.MobKiller.Message");
//                            message = message.replace("%amountofmobs%", String.valueOf(plugin.getConfig().getInt("Quests.Repeatable.MobKiller.AmountOfMobs")));
//                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
//                        }
//
//
//                    }
//
//                } catch (SQLException ex) {
//                    return;
//                }catch (NullPointerException ext){
//                    return;
//                }
//            }
//    }
//}
