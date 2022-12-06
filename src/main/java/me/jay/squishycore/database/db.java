package me.jay.squishycore.database;

import me.jay.squishycore.SquishyCore;
import org.bukkit.plugin.Plugin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {

    Plugin plugin = SquishyCore.getPlugin(SquishyCore.class);

    private Connection connection;

    public boolean isConnected(){
        return(this.connection != null);
    }

    public void connect() throws SQLException {
        String host = plugin.getConfig().getString("Database.address");
        String port = plugin.getConfig().getString("Database.port");
        String user = plugin.getConfig().getString("Database.username");
        String pass = plugin.getConfig().getString("Database.password");
        String database = plugin.getConfig().getString("Database.dbName");

        if (!isConnected()) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + URLEncoder.encode(user, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(pass, StandardCharsets.UTF_8) + "@" + host + ":" + port + "/" + database + "?autoReconnect=true");
            } catch (SQLException e) {
                plugin.getLogger().severe("! DATABASE NEEDS TO BE CONNECTED !");
            }
        }
    }

    public void disconnect(){
        if (isConnected()){
            try{
                this.connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
