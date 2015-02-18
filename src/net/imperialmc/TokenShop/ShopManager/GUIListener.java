package net.imperialmc.TokenShop.ShopManager;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUIListener implements Listener {
  private Plugin plugin = Main.getPlugin();
  
  public GUIListener(Main Main) {
    plugin.getServer().getPluginManager().registerEvents(this, Main);
  }
  
  @EventHandler
  public void onClick(InventoryClickEvent event) {
    Inventory inv = event.getInventory();
    if (inv.getName() != GUIManager.getName()) {
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
    if ((event.getCurrentItem() != null) && (event.getCurrentItem().getItemMeta() != null)) {
      ItemMeta mtea = event.getCurrentItem().getItemMeta();
      ItemLore lore;
      
      //TODO ***** Get the Item's Cost from the Lore.
      
      int cost;
      if (cost < tokens) {
        TokenManager.removeTokens(playerID, cost);
        p.getInventory().addItem(event.getCurrentItem());
        p.sendMessage("You just spent " + cost + " tokens on that item!");
        p.sendMessage("Use /tshop to buy more items!");
        return;
      }
      
    }
}
