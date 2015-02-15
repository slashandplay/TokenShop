package net.imperialmc.TokenShop;

import net.imperialmc.TokenShop.CommandManager.TShopCommand;
import net.imperialmc.TokenShop.CommandManager.TokensCommand;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  
  private static Plugin plugin;
  TokenManager tokenm = new TokenManager();
  
  public void onEnable() {
    this.saveDefaultConfig();
    plugin = this; // This is where we register our events/commands
    
    /** Register Events & Commands */
    registerEvents(this, new TokenManager());
    getCommand("tokens").setExecutor(new TokensCommand());
    getCommand("tshop").setExecutor(new TShopCommand());
  }

  // Much eaisier then registering events in 10 diffirent methods
  public static void registerEvents(org.bukkit.plugin.Plugin plugin,
      Listener... listeners) {
    for (Listener listener : listeners) {
      Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }

  public TokenManager getTokenManager() {
    return tokenm;
  }
  
  public void onDisable() {
    TokenManager.saveHashMap();
    plugin = null; // To stop memory leaks
  }
  
  public static Plugin getPlugin() {
    return plugin;
  }
  
  
  
}
