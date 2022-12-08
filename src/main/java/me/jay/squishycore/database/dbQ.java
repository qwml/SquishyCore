package me.jay.squishycore.database;

import me.jay.squishycore.SquishyCore;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class dbQ {

    private final SquishyCore plugin;
    public dbQ(SquishyCore plugin){
        this.plugin = plugin;
    }


    public void createTable() throws SQLException {
        PreparedStatement table = plugin.DB.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS quests(playerUUID varchar(255), mining int(3), blocksplaced int(3), mobskilled int(3), fish int(3), farming int(3), PRIMARY KEY(playerUUID))");
        table.executeUpdate();
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

}
