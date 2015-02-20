package net.imperialmc.TokenShop.ShopManager;

import java.util.ArrayList;

import net.imperialmc.TokenShop.Main;
import net.imperialmc.TokenShop.TokenManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager {

  private Main plugin;
  
  private Inventory shop = Bukkit.createInventory(null, 27, "§a§lImperial Factions Reborn TokenShop");
  private static String shopName;
  FileConfiguration shopData;
  
  public GUIManager() {
    //TODO Get shopdata, pull the name, replace & from shopdata
    //with the symbol below
    shopName = "§a§lImperial Factions Reborn TokenShop";
    shop = createInventory();
  }
  
  /*********************************************
   * FORMAT OF SHOPDATA CONFIG:
   * - Material.EMERALDS:2 1 $5 E16 E17 E18 name:"test"
   * - Material-Type-DataValue Quantity Price/Cost Enchantment .. Enc Enc Name
   */
  
  public Inventory createInventory() {
    shop = Bukkit.createInventory(null, 27, shopName);
    
    // TODO: Get the content from the config file into this list v
    ArrayList<String> list = new ArrayList<>();
    String itemContents;
    boolean dataValuePresent;
    String[] itemSpecifics = null;
    
    for (int i = 0; i < list.size(); i++) {
      dataValuePresent = false;
      itemContents = list.get(i);
      /** Splits the large String */
      String[] data = itemContents.split(" ");
      /** Checks the first String in the array for ':' */
      for (int j = 0; j < data[0].length(); j++) {
        if (data[0].charAt(j) == ':') {
          // Splits the String into two 
          itemSpecifics = data[0].split(":");
          dataValuePresent = true;
          break;
        }
      }
      
      int quantity = (int)Integer.getInteger(data[1]);
      ItemStack itemstack;
      
      /** If datavalue present, use the data value, if not, use simpler ItemStack constructor */
      if (dataValuePresent) {
        Material material = Material.getMaterial(itemSpecifics[0]);
        itemstack = new ItemStack(material, quantity, (short)((int)(Integer.getInteger(itemSpecifics[1]))));
      }
      else {
        Material material = Material.getMaterial(data[0]);
        itemstack = new ItemStack(material, quantity);
      }
      
      ItemMeta meta = itemstack.getItemMeta();
      
      shop.setItem(i, itemstack);
    }
    return shop;
    
  }
  
  public Inventory getShop() {
    return shop;
  }
  
  public static String getShopName() {
    return shopName;
  }
}
