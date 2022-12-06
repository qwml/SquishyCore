package me.jay.squishycore.morphTools;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class morphCommand implements CommandExecutor {


    Plugin plugin = SquishyCore.getPlugin(SquishyCore.class);



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> plore = plugin.getConfig().getStringList("MorphTools.Pickaxe.Lore");
        List<String> pickenchants = plugin.getConfig().getStringList("MorphTools.Pickaxe.Enchants");
        ItemStack pick = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Pickaxe.Material")));
        /* Pickaxe */

        ItemMeta pickmeta = pick.getItemMeta();
        pickmeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Pickaxe.Name")));
        ArrayList<String> picklore = new ArrayList<>();

        for (String pl : plore) {

            picklore.add(Color(pl));

        }

        pickmeta.setLore(picklore);
        pickmeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Pickaxe.Unbreakable"));
        for (String p : pickenchants) {

            pickmeta.addEnchant(Enchantment.getByName(p), Enchantment.getByName(p).getMaxLevel() + 2, true);

        }
        pick.setItemMeta(pickmeta);

        if (command.getName().equalsIgnoreCase("morphtool")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;


                if (player.hasPermission("squishycore.morphtools.give")) {
                    if (args.length <= 0) {

                        player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &7You must input an &aonline &7player."));

                    }else{

                        Player target = Bukkit.getPlayer(args[0]);
                        target.getInventory().addItem(pick);
                    }

                }
            }else{

                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.getInventory().addItem(pick);
                }catch (NullPointerException e){
                    plugin.getLogger().severe("Invalid Inputs.");
                }



            }
        }

            return true;

    }

    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&',s);
        return s;
    }
}
