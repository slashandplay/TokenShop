package com.slashandplay.TokenShop.ShopManager;

import java.util.List;

import net.imperialmc.TokenShop.Main;
import net.imperialmc.TokenShop.TokenManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class GUIListener implements Listener {
  private Plugin plugin = Main.pl();

  public GUIListener(Main Main) {
    plugin.getServer().getPluginManager().registerEvents(this, Main);
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    Inventory inv = event.getInventory();
    if (inv.getName().equals(GUIManager.getShopName())) {
      event.setCancelled(true);
      return;
    }
    Player p = (Player) event.getWhoClicked();
    Inventory inv2 = p.getInventory();
    String playerID = p.getUniqueId().toString();
    int tokens = TokenManager.getTokens(playerID);
    int firstEmpty = p.getInventory().firstEmpty();
    if (firstEmpty == -1) {
      p.sendMessage(ChatColor.DARK_RED + "ERROR: Your inventory is full.");
      event.setCancelled(true);
      return;
    }
    if ((event.getCurrentItem() != null)
        && (event.getCurrentItem().getItemMeta() != null)) {
      ItemMeta meta = event.getCurrentItem().getItemMeta();
      List<String> lore = meta.getLore();
      

      // TODO ***** Get the Item's Cost from the Lore.

      int cost = 0;
      if (cost < tokens) {
        TokenManager.removeTokens(playerID, cost);
        ItemStack newItem = event.getCurrentItem().clone();
        ItemMeta newMeta = newItem.getItemMeta();
        newMeta.setLore(null);
        newItem.setItemMeta(newMeta);
        
        inv2.addItem(newItem);
        p.sendMessage("You just spent " + cost + " tokens on that item!");
        p.sendMessage("Use /tshop to buy more items!");
        return;
      }

    }
  }

}
