package me.jay.squishycore.placeHolders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.jay.squishycore.SquishyCore;
import org.bukkit.OfflinePlayer;

public class placeholderAPI extends PlaceholderExpansion {

    private final SquishyCore plugin;
    public placeholderAPI(SquishyCore plugin){
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Jay";
    }

    @Override
    public String getIdentifier() {
        return "squishycore";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("pvptoggle")) {
            String Toggle = null;
            if (plugin.toggledON.containsKey(player.getUniqueId())){
                Toggle = "PVP ENABLED";
            }else{
                Toggle = "PVP DISABLED";
            }
        }else if (params.equalsIgnoreCase("amountofblocksmined")){
            return plugin.amountofblocks.toString();
        }else if (params.equalsIgnoreCase("amountofmobs")) {
            return plugin.amountofmobs.toString();
        }else if (params.equalsIgnoreCase("amountofblocksplaced")){
            return plugin.amountofblocksplaced.toString();
        }else if (params.equalsIgnoreCase("amountoffish")){
            return plugin.amountoffish.toString();
        }else if (params.equalsIgnoreCase("amountofcrops")){
            return plugin.amountofcrops.toString();
        }
        return null; // Placeholder is unknown by the Expansion
    }
}
