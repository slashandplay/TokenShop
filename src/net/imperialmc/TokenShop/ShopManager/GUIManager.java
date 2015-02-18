package net.imperialmc.TokenShop.ShopManager;

import java.util.UUID;

import net.imperialmc.TokenShop.Main;
import net.imperialmc.TokenShop.TokenManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUIManager implements CommandExecutor {

  private Main plugin;
  
  private static Inventory shop = Bukkit.createInventory(null, 27, "§a§lImperial Factions Reborn TokenShop");
  
  public Inventory getInventory() {
    return shop;
  }
  
  static {
    InventoryListener.itemk();
  }

}
