package me.jay.squishycore;

import me.jay.squishycore.commandBlocker.blockedCommands;
import me.jay.squishycore.greetingsMessages.joinleaveEvent;
import me.jay.squishycore.morphTools.morphCommand;
import me.jay.squishycore.morphTools.morphEvent;
import me.jay.squishycore.placeHolders.placeholderAPI;
import me.jay.squishycore.pvpToggle.pvptoggleCommand;
import me.jay.squishycore.pvpToggle.pvptoggleEvent;
import me.jay.squishycore.quests.*;
import me.jay.squishycore.staffCore.staffChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class SquishyCore extends JavaPlugin implements Listener {

    public HashMap<UUID, String> toggledON = new HashMap<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&8[&d&lSquishy&b&lCore&8] &aStarting :D!"));
        getCommand("sc").setExecutor(new staffChat());
        getServer().getPluginManager().registerEvents(new joinleaveEvent(), this);
        getServer().getPluginManager().registerEvents(new morphEvent(), this);
        getServer().getPluginManager().registerEvents(new blockedCommands(this), this);
        getServer().getPluginManager().registerEvents(new pvptoggleEvent(this), this);
        getCommand("morphtool").setExecutor(new morphCommand());
        getCommand("pvp").setExecutor(new pvptoggleCommand(this));
        config();
        /*h2Database.initializeData();*/
        new placeholderAPI(this).register();


    }

    public void config(){

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
