package me.jay.squishycore.morphTools;

import me.jay.squishycore.SquishyCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class morphEvent implements Listener {

    Plugin plugin = SquishyCore.getPlugin(SquishyCore.class);
    /* ItemStacks */

    ItemStack pick = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Pickaxe.Material")));
    ItemStack silk = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Silk.Material")));
    ItemStack filler = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.GUI.Filler")));
    ItemStack axe = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Axe.Material")));
    ItemStack shovel = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Spade.Material")));
    ItemStack hoe = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Hoe.Material")));
    ItemStack fish = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.FishingRod.Material")));
    ItemStack picksel = new ItemStack(Material.valueOf(plugin.getConfig().getString("MorphTools.Pickaxe.Material")));


    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e){

        Player player = e.getPlayer();

        /* Enchants */

        List<String> pickenchants = plugin.getConfig().getStringList("MorphTools.Pickaxe.Enchants");
        List<String> axeenchants = plugin.getConfig().getStringList("MorphTools.Axe.Enchants");
        List<String> rodenchants = plugin.getConfig().getStringList("MorphTools.FishingRod.Enchants");
        List<String> hoeenchants = plugin.getConfig().getStringList("MorphTools.Hoe.Enchants");
        List<String> shovelenchants = plugin.getConfig().getStringList("MorphTools.Spade.Enchants");
        List<String> silkenchants = plugin.getConfig().getStringList("MorphTools.Silk.Enchants");

        /* Lore */

        List<String> plore = plugin.getConfig().getStringList("MorphTools.Pickaxe.Lore");
        List<String> alore = plugin.getConfig().getStringList("MorphTools.Axe.Lore");
        List<String> slore= plugin.getConfig().getStringList("MorphTools.Spade.Lore");
        List<String> rlore = plugin.getConfig().getStringList("MorphTools.FishingRod.Lore");
        List<String> hlore = plugin.getConfig().getStringList("MorphTools.Hoe.Lore");
        List<String> sslore = plugin.getConfig().getStringList("MorphTools.Silk.Lore");


        /* Pickaxe */

        ItemMeta pickmeta = pick.getItemMeta();
        pickmeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Pickaxe.Name")));
        ArrayList<String> picklore = new ArrayList<>();

        for (String pl : plore){

            picklore.add(Color(pl));

        }

        pickmeta.setLore(picklore);
        pickmeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Pickaxe.Unbreakable"));
        for (String p : pickenchants){

            pickmeta.addEnchant(Enchantment.getByName(p), Enchantment.getByName(p).getMaxLevel() + 2, true);

        }
        pick.setItemMeta(pickmeta);

        /* SilkTouch Pickaxe */

        ItemMeta silkmeta = silk.getItemMeta();
        silkmeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Silk.Name")));
        ArrayList<String> silklore = new ArrayList<>();

        for (String sl : sslore){

            silklore.add(Color(sl));

        }

        silkmeta.setLore(silklore);
        silkmeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Silk.Unbreakable"));
        for (String sss : silkenchants){


            silkmeta.addEnchant(Enchantment.getByName(sss), Enchantment.getByName(sss).getMaxLevel() + 2, true);

        }
        silk.setItemMeta(silkmeta);


        /* Axe */

        ItemMeta axemeta = axe.getItemMeta();
        axemeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Axe.Name")));
        ArrayList<String> axelore = new ArrayList<>();

        for (String al : alore){

            axelore.add(Color(al));

        }

        axemeta.setLore(axelore);
        axemeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Axe.Unbreakable"));
        for (String ae : axeenchants){

            axemeta.addEnchant(Enchantment.getByName(ae), Enchantment.getByName(ae).getMaxLevel() + 2, true);

        }
        axe.setItemMeta(axemeta);

        /* Spade */

        ItemMeta spademeta = shovel.getItemMeta();
        spademeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Spade.Name")));
        ArrayList<String> spadelore = new ArrayList<>();

        for (String sl : slore){

            spadelore.add(Color(sl));

        }

        spademeta.setLore(spadelore);
        spademeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Spade.Unbreakable"));
        for (String se : shovelenchants){

            spademeta.addEnchant(Enchantment.getByName(se), Enchantment.getByName(se).getMaxLevel() + 2, true);

        }
        shovel.setItemMeta(spademeta);

        /* Hoe */

        ItemMeta hoemeta = hoe.getItemMeta();
        hoemeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.Hoe.Name")));
        ArrayList<String> hoelore = new ArrayList<>();

        for (String hl : hlore){

            hoelore.add(Color(hl));

        }

        hoemeta.setLore(hoelore);
        hoemeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.Hoe.Unbreakable"));
        for (String he : hoeenchants){

            hoemeta.addEnchant(Enchantment.getByName(he), Enchantment.getByName(he).getMaxLevel() + 2, true);

        }
        hoe.setItemMeta(hoemeta);

        /* Rod */

        ItemMeta rodmeta = fish.getItemMeta();
        rodmeta.setDisplayName(Color(plugin.getConfig().getString("MorphTools.FishingRod.Name")));
        ArrayList<String> rodlore = new ArrayList<>();

        for (String rl : rlore){

            rodlore.add(Color(rl));

        }

        rodmeta.setLore(rodlore);
        rodmeta.setUnbreakable(plugin.getConfig().getBoolean("MorphTools.FishingRod.Unbreakable"));
        for (String re : rodenchants){

            rodmeta.addEnchant(Enchantment.getByName(re), Enchantment.getByName(re).getMaxLevel() + 2, true);

        }
        fish.setItemMeta(rodmeta);

        ItemMeta fi = filler.getItemMeta();
        fi.setDisplayName(Color("&7"));
        filler.setItemMeta(fi);


        ItemMeta ps = picksel.getItemMeta();
        ps.setDisplayName(Color("&7Select a pickaxe."));
        picksel.setItemMeta(ps);


        if (e.getAction().isRightClick()) {
            if (player.isSneaking()) {

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {

                    Inventory i = Bukkit.createInventory(null, 27, Color("&aMorph Tools."));

                    ItemStack[] menuItems = {filler, filler, filler, filler, filler, filler, filler, filler, filler,
                                            filler, filler, picksel, axe, shovel, hoe, fish, filler, filler,
                                            filler, filler, filler, filler, filler, filler, filler, filler, filler};


                    i.setContents(menuItems);


                    player.openInventory(i);

                }

            }
        }

    }

    @EventHandler
    public void SwapItem(InventoryClickEvent evt){

        Player player = (Player) evt.getWhoClicked();

        if (evt.getView().getTitle().equalsIgnoreCase(Color("&aMorph Tools."))){

            evt.setCancelled(true);

            int Slot = evt.getRawSlot() + 1;

            if (Slot == 12){


                    Inventory pickinv = Bukkit.createInventory(null, 27, Color("&cPickaxe Selection"));

                    ItemStack[] menuItems = {filler, filler, filler, filler, filler, filler, filler, filler, filler,
                            filler, filler, filler, pick, filler, silk, filler, filler, filler,
                            filler, filler, filler, filler, filler, filler, filler, filler, filler};

                    pickinv.setContents(menuItems);
                    player.openInventory(pickinv);


            }else if (Slot == 13){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, axe);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));
                }

            }else if (Slot == 14){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, shovel);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));
                }

            }else if (Slot == 15){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, hoe);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));
                }

            }else if (Slot == 16){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, fish);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));

                }

            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent evte){
        Player player = (Player) evte.getWhoClicked();
        if (evte.getView().getTitle().equalsIgnoreCase(Color("&cPickaxe Selection"))){
            evte.setCancelled(true);

            int slot = evte.getRawSlot() + 1;

            if (slot == 13){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, pick);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));
                }

            }else if (slot == 15){

                if (player.getInventory().getItemInMainHand().equals(pick) || player.getInventory().getItemInMainHand().equals(silk) || player.getInventory().getItemInMainHand().equals(axe) || player.getInventory().getItemInMainHand().equals(fish) || player.getInventory().getItemInMainHand().equals(hoe) || player.getInventory().getItemInMainHand().equals(shovel)) {
                    int remove = player.getInventory().getHeldItemSlot();
                    player.getInventory().clear(remove);
                    player.getInventory().setItem(remove, silk);
                }else{
                    player.sendMessage(Color("&8[&d&lSquishy&b&lCore&8] &cYou must hold a morph tool to perform this magic."));
                }

            }


        }

    }


    private String Color(String s){
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

}
