package me.jay.squishycore.database;

import me.jay.squishycore.SquishyCore;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.time.Instant;

public class dbQ {

    private final SquishyCore plugin;
    public dbQ(SquishyCore plugin){
        this.plugin = plugin;
    }


    public void createTable() throws SQLException {
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS quests(playerUUID varchar(255), mining int(3), blocksplaced int(3), mobskilled int(3), fish int(3), farming int(3), PRIMARY KEY(playerUUID))");
        table.executeUpdate();
        PreparedStatement playtimesTable = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playtimes(playerUUID varchar(255), seconds BIGINT, lastjoinTime BIGINT, PRIMARY KEY(playerUUID))");
        playtimesTable.executeUpdate();
        PreparedStatement pwarpTable = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS pwarps(warpID int NOT NULL AUTO_INCREMENT, playerUUID varchar(255), warpName varchar(255), location varchar(255), PRIMARY KEY(warpID))");
        pwarpTable.executeUpdate();
    }

    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO quests(playerUUID,mining,blocksplaced,mobskilled,fish,farming) VALUES (?,?,?,?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setString(2, "0");
            createPlayer.setString(3, "0");
            createPlayer.setString(4, "0");
            createPlayer.setString(5, "0");
            createPlayer.setString(6, "0");
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM quests WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

    public void playtimesSetLastjoin(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM playtimes WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        long unixTimestamp = Instant.now().getEpochSecond();
        if ( rs1.next() ) {
            PreparedStatement updatePlaytimeRow = plugin.DB.getConnection().prepareStatement("UPDATE playtimes SET lastjoinTime = ? WHERE playerUUID = ?");
            updatePlaytimeRow.setLong(1, unixTimestamp);
            updatePlaytimeRow.setString(2, uuid.toString());
            updatePlaytimeRow.executeUpdate();
        } else {
            PreparedStatement createPlaytimeRow = plugin.DB.getConnection().prepareStatement("INSERT IGNORE INTO playtimes(playerUUID,seconds,lastjoinTime) VALUES (?,?,?)");
            createPlaytimeRow.setString(1, uuid.toString());
            createPlaytimeRow.setInt(2, 0);
            createPlaytimeRow.setLong(3, unixTimestamp);
            createPlaytimeRow.executeUpdate();
        }
    }

    public void playtimePlayerLeaveHandler(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();

        PreparedStatement ps1 = plugin.DB.getConnection().prepareStatement("SELECT * FROM playtimes WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        long unixTimestamp = Instant.now().getEpochSecond();
        if ( rs1.next() ) {
            long playerSeconds = rs1.getLong("seconds");
            long playerJoinTimestamp = rs1.getLong("lastjoinTime");
            long playerPlaytime = unixTimestamp - playerJoinTimestamp;
            long playerTotalPlaytime = playerSeconds + playerPlaytime;
            PreparedStatement updatePlaytimeRow = plugin.DB.getConnection().prepareStatement("UPDATE playtimes SET seconds = ? WHERE playerUUID = ?");
            if(rs1.getLong("seconds") != 3600) {
                updatePlaytimeRow.setLong(1, playerTotalPlaytime);
                updatePlaytimeRow.setString(2, uuid.toString());
            }else{
                updatePlaytimeRow.setLong(1, 0);
                updatePlaytimeRow.setString(2, uuid.toString());
            }
            updatePlaytimeRow.executeUpdate();
        }
    }


}
