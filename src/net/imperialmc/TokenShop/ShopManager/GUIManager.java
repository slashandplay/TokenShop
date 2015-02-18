package net.imperialmc.TokenShop.ShopManager;

import java.util.UUID;
import java.util.ArrayList;

import net.imperialmc.TokenShop.Main;
import net.imperialmc.TokenShop.TokenManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUIManager implements CommandExecutor {

  private Main plugin;
  
  private Inventory shop = Bukkit.createInventory(null, 27, "§a§lImperial Factions Reborn TokenShop");
  private String shopName = "§a§lImperial Factions Reborn TokenShop";
  FileConfiguration shopData;
  
  public GUIManager() {
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
    ArrayList<String>() list = new ArrayList<>();
    String itemContents;
    boolean dataValuePresent;
    
    for (int i = 0; i < list.size(); i++) {
      dataValuePresent = false;
      itemContents = list.get(i);
      /** Splits the large String */
      String[] data = litemContents.split();
      /** Checks the first String in the array for ':' */
      for (int j = 0; j < data[0].length() j++) {
        if (data[0].charAt(j).equals(':')) {
          // Splits the String into two 
          String[] itemSpecifics = data[0].split(':');
          dataValuePresent = true;
          break;
        }
      }
      
      int quantity = (int)Integer.getInteger(data[1]);
      
      /** If datavalue present, use the data value, if not, use simpler ItemStack constructor */
      if (dataValuePresent) {
        Material material = Material.getMaterial(itemSpecifics[0]);
        ItemStack itemstack = new ItemStack(material, quantity, (Short)Integer.getInteger(itemSpecifics[1]));
      }
      else {
        Material material = Material.getMaterial(data[0]);
        ItemStack itemstack = new ItemStack(material, quantity);
      }
      
      ItemMeta meta = itemstack.getItemMeta();
      
      shop.setItem(i, itemstack);
    }
    
    Material material = Material.EMERALD;
    int quantity = 1;
    ItemStack itemstack = new ItemStack(material, quantitiy);
    
    shop.setItem(i, newstack);

  }
  
  public Inventory getInventory() {
    return shop;
  }
  
  private String getShopName() {
    return shopname;
  }
}
