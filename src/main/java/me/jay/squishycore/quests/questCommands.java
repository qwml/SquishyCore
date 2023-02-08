//package me.jay.squishycore.quests;
//
//import me.jay.squishycore.SquishyCore;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Item;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//
//public class questCommands implements CommandExecutor {
//
//    private final SquishyCore plugin;
//    public questCommands(SquishyCore plugin){
//        this.plugin = plugin;
//    }
//
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//
//
//        if (command.getName().equalsIgnoreCase("quests")){
//
//
//            if (sender instanceof Player){
//
//                Player player = (Player) sender;
//
//                Inventory questI = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', "&cQuest GUI"));
//
//                ItemStack filler = new ItemStack(Material.PINK_STAINED_GLASS_PANE, 1);
//                ItemStack mining = new ItemStack(Material.STONE_PICKAXE, 1);
//                ItemStack fishing = new ItemStack(Material.TROPICAL_FISH, 1);
//                ItemStack farming = new ItemStack(Material.WHEAT, 1);
//                ItemStack mobkill = new ItemStack(Material.ROTTEN_FLESH, 1);
//                ItemStack blockplace = new ItemStack(Material.STONE, 1);
//
//                ItemMeta mininmeta = mining.getItemMeta();
//                mininmeta.setDisplayName(Color("&5Mining Quest:"));
//                ArrayList<String> mlore = new ArrayList<>();
//                mlore.add(Color("&8"));
//                mlore.add(Color("&5&oYou are currently " + plugin.amountofblocks + " out of 500."));
//                mlore.add(Color("&7Break blocks to complete this quest."));
//                mlore.add(Color("&8"));
//                mininmeta.setLore(mlore);
//                mining.setItemMeta(mininmeta);
//
//                ItemMeta fishinmeta = fishing.getItemMeta();
//                fishinmeta.setDisplayName(Color("&eFishing Quest:"));
//                ArrayList<String> flore = new ArrayList<>();
//                flore.add(Color("&8"));
//                flore.add(Color("&e&oYou are currently " + plugin.amountoffish + " out of 500."));
//                flore.add(Color("&7Catch COD to complete this quest."));
//                flore.add(Color("&8"));
//                fishinmeta.setLore(flore);
//                fishing.setItemMeta(fishinmeta);
//
//                ItemMeta blockmeta = blockplace.getItemMeta();
//                blockmeta.setDisplayName(Color("&dBlock Place Quest:"));
//                ArrayList<String> bplore = new ArrayList<>();
//                bplore.add(Color("&8"));
//                bplore.add(Color("&d&oYou are currently " + plugin.amountofblocksplaced + " out of 500."));
//                bplore.add(Color("&7Place Blocks to complete this quest."));
//                bplore.add(Color("&8"));
//                blockmeta.setLore(bplore);
//                blockplace.setItemMeta(blockmeta);
//
//                ItemMeta mobmeta = mobkill.getItemMeta();
//                mobmeta.setDisplayName(Color("&cMob Killing Quest:"));
//                ArrayList<String> moblore = new ArrayList<>();
//                moblore.add(Color("&8"));
//                moblore.add(Color("&c&oYou are currently " + plugin.amountofmobs + " out of 500."));
//                moblore.add(Color("&7Kill Hostile Mobs to complete this quest."));
//                moblore.add(Color("&8"));
//                mobmeta.setLore(moblore);
//                mobkill.setItemMeta(mobmeta);
//
//                ItemMeta farmmeta = farming.getItemMeta();
//                farmmeta.setDisplayName(Color("&2Farming Quest:"));
//                ArrayList<String> farmlore = new ArrayList<>();
//                farmlore.add(Color("&8"));
//                farmlore.add(Color("&2&oYou are currently " + plugin.amountofcrops + " out of 500."));
//                farmlore.add(Color("&7Hoe crops to complete this quest."));
//                farmlore.add(Color("&8"));
//                farmmeta.setLore(farmlore);
//                farming.setItemMeta(farmmeta);
//
//
//                ItemStack[] menuItems = {filler, filler, filler, filler, filler, filler, filler, filler, filler,
//                        filler, filler, mining, blockplace, mobkill, fishing, farming, filler, filler,
//                        filler, filler, filler, filler, filler, filler, filler, filler, filler};
//
//
//                questI.setContents(menuItems);
//
//                player.openInventory(questI);
//
//            }
//
//
//        }
//
//
//        return true;
//    }
//
//    private String Color(String s){
//        s= ChatColor.translateAlternateColorCodes('&',s);
//        return s;
//    }
//}
